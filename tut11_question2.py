"""
Tutorial - 11 (Question 2)
--------------------------
Problem Statement:
A weather monitoring station collects temperature readings from multiple sensors
and transmits them to a central monitoring server. Develop a Python-based UDP
client–server application in which the client sends temperature readings to the
server, and the server receives and displays the readings. Justify the suitability
of UDP for this application compared with TCP.

Usage:
  - Default (Self-Test Demo) : py tut11_question2.py
  - Dedicated Server Mode     : py tut11_question2.py server [host] [port]
  - Dedicated Client Mode     : py tut11_question2.py client [host] [port]
"""

import sys
import json
import time
import socket
import datetime
import threading

DEFAULT_HOST = "127.0.0.1"
DEFAULT_PORT = 65433


# ====================================================================
# UDP SERVER IMPLEMENTATION
# ====================================================================
def run_udp_server(host=DEFAULT_HOST, port=DEFAULT_PORT, is_daemon=False, stop_event=None):
    """
    UDP Central Monitoring Server.
    Listens for incoming datagrams from weather sensors and displays readings.
    """
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind((host, port))
    server_socket.settimeout(1.0)

    print(f"[Server] Central Weather Monitoring UDP Server listening on {host}:{port}")

    try:
        while stop_event is None or not stop_event.is_set():
            try:
                data, addr = server_socket.recvfrom(2048)
                reading = json.loads(data.decode("utf-8"))

                sensor_id = reading.get("sensor_id", "Unknown")
                temp_c = reading.get("temperature_c", 0.0)
                humidity = reading.get("humidity_pct", 0.0)
                location = reading.get("location", "N/A")
                timestamp = reading.get("timestamp", "N/A")

                print(f"  [Server Received @ {timestamp}]")
                print(f"    From Sensor IP : {addr[0]}:{addr[1]}")
                print(f"    Sensor ID      : {sensor_id} ({location})")
                print(f"    Temperature    : {temp_c:.1f} °C ({(temp_c * 9/5) + 32:.1f} °F)")
                print(f"    Humidity       : {humidity:.1f}%")
                print("    " + "-" * 40)
            except socket.timeout:
                continue
            except json.JSONDecodeError:
                print(f"  [Server Warning] Malformed packet from {addr}")
    finally:
        server_socket.close()
        print("[Server] UDP Server stopped.")


# ====================================================================
# UDP CLIENT (WEATHER SENSOR SIMULATOR)
# ====================================================================
def send_sensor_reading(sensor_id, location, temp_c, humidity, host=DEFAULT_HOST, port=DEFAULT_PORT):
    """Sends a single weather packet using UDP datagram."""
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        payload = {
            "sensor_id": sensor_id,
            "location": location,
            "temperature_c": temp_c,
            "humidity_pct": humidity,
            "timestamp": datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        }
        data = json.dumps(payload).encode("utf-8")
        client_socket.sendto(data, (host, port))
    finally:
        client_socket.close()


def interactive_sensor_client(host=DEFAULT_HOST, port=DEFAULT_PORT):
    """Interactive client to simulate sending sensor telemetry."""
    print(f"\n=== Weather Station Sensor UDP Client (Target: {host}:{port}) ===")
    print("Send temperature readings. Enter 'quit' to exit.\n")
    sensor_id = input("Enter Sensor Identifier (e.g. SENSOR-NORTH): ").strip() or "SENSOR-01"
    location = input("Enter Location (e.g. Roof Tower): ").strip() or "Station Alpha"

    while True:
        try:
            val = input("\nEnter Temperature in Celsius (or 'quit'): ").strip()
            if val.lower() in ("quit", "exit", "q"):
                break
            temp_c = float(val)
            humidity = float(input("Enter Humidity Percentage (e.g. 58): ").strip() or "50")

            send_sensor_reading(sensor_id, location, temp_c, humidity, host, port)
            print(f"[Client] Sent datagram: {temp_c}°C, {humidity}% RH to server.")
        except ValueError:
            print("Invalid numeric value, please try again.")
        except (KeyboardInterrupt, EOFError):
            break


# ====================================================================
# JUSTIFICATION OF UDP OVER TCP
# ====================================================================
def print_udp_justification():
    justification = """
======================================================================
JUSTIFICATION: Why UDP is more suitable than TCP for Weather Sensors
======================================================================
1. Minimal Protocol Overhead:
   - UDP is connectionless. There is no 3-way handshake (SYN, SYN-ACK, ACK)
     before sending data and no 4-way connection teardown.
   - UDP header is only 8 bytes compared to TCP's 20-60 bytes, saving bandwidth
     and power for battery-operated IoT weather sensors.

2. Tolerance to Packet Loss (Ephemeral Data):
   - Periodic sensor telemetry is time-sensitive and transient. If an occasional
     reading is lost, it is immediately superseded by the next reading (e.g. 2s later).
   - In contrast, TCP's retransmission mechanism would waste resources delivering
     outdated data.

3. No Head-of-Line (HoL) Blocking & Lower Latency:
   - TCP enforces strict in-order delivery; a single dropped packet stalls all
     subsequent packets until retransmitted. UDP delivers packets as they arrive,
     preserving real-time streaming performance.

4. Power & Resource Efficiency:
   - Embedded microcontrollers (like ESP32/Arduino) have limited RAM and power.
     UDP avoids maintaining complex transmission control blocks (TCBs), sequence
     counters, and sliding window buffers.
======================================================================
"""
    print(justification.strip())


# ====================================================================
# AUTOMATED SELF-TEST DEMO
# ====================================================================
def run_demo():
    print("=" * 70)
    print("TUTORIAL 11 - QUESTION 2: UDP Weather Monitoring Client-Server System")
    print("=" * 70)

    stop_event = threading.Event()
    server_thread = threading.Thread(
        target=run_udp_server,
        args=(DEFAULT_HOST, DEFAULT_PORT, True, stop_event),
        daemon=True
    )
    server_thread.start()
    time.sleep(0.3)

    sample_readings = [
        ("SENSOR-NORTH-01", "Rooftop Mast", 28.4, 62.0),
        ("SENSOR-SOUTH-02", "Greenhouse", 34.1, 78.5),
        ("SENSOR-EAST-03", "Valley Station", 22.8, 55.0),
        ("SENSOR-WEST-04", "Wind Turbine A", 25.6, 60.2),
    ]

    print("\n[Client] Simulating 4 weather sensors transmitting UDP datagrams...\n")
    for s_id, loc, temp, hum in sample_readings:
        print(f">>> [Client Transmit] {s_id} -> Temp: {temp}°C, Humidity: {hum}%")
        send_sensor_reading(s_id, loc, temp, hum, DEFAULT_HOST, DEFAULT_PORT)
        time.sleep(0.25)

    time.sleep(0.5)
    stop_event.set()
    server_thread.join(timeout=2)

    print("\n")
    print_udp_justification()
    print("=" * 70)


if __name__ == "__main__":
    if len(sys.argv) > 1:
        mode = sys.argv[1].lower()
        host = sys.argv[2] if len(sys.argv) > 2 else DEFAULT_HOST
        port = int(sys.argv[3]) if len(sys.argv) > 3 else DEFAULT_PORT

        if mode == "server":
            run_udp_server(host, port)
        elif mode == "client":
            interactive_sensor_client(host, port)
        elif mode == "justification":
            print_udp_justification()
        else:
            print("Unknown mode. Use 'server', 'client', 'justification', or run without args for demo.")
    else:
        run_demo()
