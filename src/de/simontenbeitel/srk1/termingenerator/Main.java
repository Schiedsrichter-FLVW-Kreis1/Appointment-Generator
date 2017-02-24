package de.simontenbeitel.srk1.termingenerator;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        InputStream appointmentsInputStream = new FileInputStream(new File("termine.csv"));
        BufferedReader appointmentsReader = new BufferedReader(new InputStreamReader(appointmentsInputStream, "Cp1252"));
        List<Appointment> appointments = appointmentsReader.lines()
                .skip(1)
                .map(mapAppointmentCsvToPojo)
                .collect(Collectors.toList());
        OutputStream appointmentsOutputStream = new FileOutputStream(new File("termine.html"));
        BufferedWriter appointmentsWriter = new BufferedWriter(new OutputStreamWriter(appointmentsOutputStream, "UTF-8"));
        appointments.stream()
                .map(mapAppointmentPojoToHtml)
                .forEach((str) -> {
                    try {
                        System.out.println(str);
                        appointmentsWriter.write(str + LINESEPERATOR);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        appointmentsWriter.flush();
    }

    private static final String DELIMINATOR = ";";
    private static final String LINESEPERATOR = System.lineSeparator();

    private static Function<String, Appointment> mapAppointmentCsvToPojo = line -> {
        String[] parts = line.split(DELIMINATOR);
        String dayOfTheWeek = parts[0];
        String date = parts[1];
        String time = parts[2];
        String description = parts[3];
        String location = parts[4];
        return new Appointment(dayOfTheWeek, date, time, description, location);
    };

    private static Function<Appointment, String> mapAppointmentPojoToHtml = appointment ->
            "<tr>" +
                    LINESEPERATOR +
                    getCellForContent(appointment.date == null ? "" : appointment.date) +
                    LINESEPERATOR +
                    getCellForContent(appointment.description == null ? "" : appointment.description) +
                    LINESEPERATOR +
                    getCellForContent(appointment.location == null ? "" : appointment.location) +
                    LINESEPERATOR +
                    "</tr>";

    private static String getCellForContent(final String content) {
        return "<td>" +
                content +
                "</td>";
    }

}
