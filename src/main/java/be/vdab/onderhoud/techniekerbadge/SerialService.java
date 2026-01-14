package be.vdab.onderhoud.techniekerbadge;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;


    @Service
    public class SerialService {
        private SerialPort port;
        private String portNaam;
        public String startSerialCommunication() throws Exception {

            SerialPort[] ports = SerialPort.getCommPorts();
            for (SerialPort p : ports) {
                 portNaam = p.getSystemPortName();
            }

            port = SerialPort.getCommPort(portNaam);
            port.setBaudRate(9600);
            port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

            if (port.openPort()) {
                System.out.println("Poort geopend");
                Thread.sleep(5000);

                try (
                        OutputStream out = port.getOutputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()))
                ) {
                    out.write("GeefNaam\n".getBytes());
                    out.flush();
                    System.out.println("Java-Service vraag naam");

                    String response = reader.readLine();
                    System.out.println("Arduino antwoordt: " + response);
                    return  response;
                } catch (Exception e) {
                    System.out.println("Fout tijdens communicatie: " + e.getMessage());
                } finally {
                    port.closePort();
                    System.out.println("Poort gesloten");
                }
            } else {
                System.out.println("Kon de poort niet openen");
            }
            return "FOUT: poort niet geopend";
        }

        public boolean isArduinoConnected() {
            SerialPort[] ports = SerialPort.getCommPorts();
            for (SerialPort p : ports) {
                String name = p.getSystemPortName();
                if (name.equals("COM6") || (name.equals("COM7"))) {
                    return true;
                }
            }
            return false;
        }

        }


