"""
Tutorial - 11 (Question 1)
--------------------------
Problem Statement:
A college wants to develop a student information system in which students can
request their examination results from a central server. Develop a Python-based
TCP client–server application in which the client sends a student's register number
to the server, and the server retrieves and sends the corresponding result to the client.

Usage:
  - Default (Self-Test Demo) : py tut11_question1.py
  - Dedicated Server Mode     : py tut11_question1.py server [host] [port]
  - Dedicated Client Mode     : py tut11_question1.py client [host] [port]
"""

import sys
import json
import time
import socket
import threading

DEFAULT_HOST = "127.0.0.1"
DEFAULT_PORT = 65432

# Central Database of Student Examination Results
STUDENT_DATABASE = {
    "REG101": {
        "name": "Arun Kumar",
        "department": "Computer Science & Engineering",
        "semester": 5,
        "gpa": 8.75,
        "result": "PASS",
        "grades": {"Python Programming": "A+", "Data Structures": "A", "Computer Networks": "A"}
    },
    "REG102": {
        "name": "Bala Subramanian",
        "department": "Information Technology",
        "semester": 5,
        "gpa": 7.40,
        "result": "PASS",
        "grades": {"Python Programming": "B+", "Data Structures": "B", "Computer Networks": "A"}
    },
    "REG103": {
        "name": "Charan Teja",
        "department": "Artificial Intelligence & Data Science",
        "semester": 5,
        "gpa": 9.30,
        "result": "PASS (Distinction)",
        "grades": {"Python Programming": "O", "Data Structures": "O", "Computer Networks": "A+"}
    },
    "REG104": {
        "name": "Divya Ramesh",
        "department": "Computer Science & Engineering",
        "semester": 5,
        "gpa": 6.80,
        "result": "PASS",
        "grades": {"Python Programming": "B", "Data Structures": "C", "Computer Networks": "B+"}
    }
}


# ====================================================================
# TCP SERVER IMPLEMENTATION
# ====================================================================
def handle_client_connection(client_socket, client_address):
    """Handles an individual client connection over TCP."""
    try:
        data = client_socket.recv(1024).decode("utf-8").strip()
        if not data:
            return

        reg_no = data.upper()
        print(f"  [Server] Received query for Register No: '{reg_no}' from {client_address}")

        if reg_no in STUDENT_DATABASE:
            student_info = STUDENT_DATABASE[reg_no]
            response = {
                "status": "SUCCESS",
                "register_no": reg_no,
                "data": student_info
            }
        else:
            response = {
                "status": "NOT_FOUND",
                "register_no": reg_no,
                "message": f"No student record found for Register Number: {reg_no}"
            }

        client_socket.sendall(json.dumps(response).encode("utf-8"))
    except Exception as e:
        err_resp = {"status": "ERROR", "message": str(e)}
        client_socket.sendall(json.dumps(err_resp).encode("utf-8"))
    finally:
        client_socket.close()


def run_tcp_server(host=DEFAULT_HOST, port=DEFAULT_PORT, is_daemon=False, stop_event=None):
    """Starts the TCP Server to listen for student result requests."""
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind((host, port))
    server_socket.listen(5)
    server_socket.settimeout(1.0)

    print(f"[Server] TCP Student Information Server listening on {host}:{port}")

    try:
        while stop_event is None or not stop_event.is_set():
            try:
                client_socket, client_address = server_socket.accept()
                t = threading.Thread(target=handle_client_connection, args=(client_socket, client_address), daemon=True)
                t.start()
            except socket.timeout:
                continue
    finally:
        server_socket.close()
        print("[Server] TCP Server shut down.")


# ====================================================================
# TCP CLIENT IMPLEMENTATION
# ====================================================================
def query_student_result(reg_no, host=DEFAULT_HOST, port=DEFAULT_PORT):
    """TCP Client function to request student result by Register Number."""
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        client_socket.connect((host, port))
        client_socket.sendall(reg_no.strip().encode("utf-8"))

        raw_response = client_socket.recv(4096).decode("utf-8")
        response = json.loads(raw_response)
        return response
    except Exception as e:
        return {"status": "CONNECTION_ERROR", "message": str(e)}
    finally:
        client_socket.close()


def display_result(response):
    """Nicely formats and prints the server response."""
    if response.get("status") == "SUCCESS":
        data = response["data"]
        print(f"\n  --- Examination Result for [{response['register_no']}] ---")
        print(f"  Name       : {data['name']}")
        print(f"  Department : {data['department']}")
        print(f"  Semester   : {data['semester']}")
        print(f"  GPA        : {data['gpa']}")
        print(f"  Result     : {data['result']}")
        print("  Subject Grades:")
        for subject, grade in data["grades"].items():
            print(f"    - {subject:22}: {grade}")
    elif response.get("status") == "NOT_FOUND":
        print(f"\n  [!] {response['message']}")
    else:
        print(f"\n  [ERROR] {response.get('message', 'Unknown Error')}")


def interactive_client(host=DEFAULT_HOST, port=DEFAULT_PORT):
    """Interactive CLI client prompt."""
    print(f"\n=== Student Information System TCP Client (Connected to {host}:{port}) ===")
    print("Type 'exit' or 'quit' to close the client.\n")
    while True:
        try:
            reg_no = input("Enter Student Register Number (e.g., REG101): ").strip()
            if reg_no.lower() in ("exit", "quit", "q"):
                print("Exiting client.")
                break
            if not reg_no:
                continue
            res = query_student_result(reg_no, host, port)
            display_result(res)
            print("-" * 50)
        except (KeyboardInterrupt, EOFError):
            print("\nClient terminated.")
            break


# ====================================================================
# AUTOMATED SELF-TEST DEMONSTRATION
# ====================================================================
def run_demo():
    print("=" * 70)
    print("TUTORIAL 11 - QUESTION 1: TCP Client-Server Student Information System")
    print("=" * 70)

    stop_event = threading.Event()
    server_thread = threading.Thread(
        target=run_tcp_server,
        args=(DEFAULT_HOST, DEFAULT_PORT, True, stop_event),
        daemon=True
    )
    server_thread.start()
    time.sleep(0.3)  # Give server brief moment to bind

    test_queries = ["REG101", "REG103", "REG999"]
    print("\n[Client] Starting automated TCP client requests...")

    for reg_no in test_queries:
        print(f"\n>>> [Client] Sending TCP Request for Register No: '{reg_no}'")
        response = query_student_result(reg_no, DEFAULT_HOST, DEFAULT_PORT)
        display_result(response)

    time.sleep(0.3)
    stop_event.set()
    server_thread.join(timeout=2)
    print("\n" + "=" * 70)
    print("Demo completed successfully. You can also run standalone:")
    print("  Server: py tut11_question1.py server")
    print("  Client: py tut11_question1.py client")
    print("=" * 70)


if __name__ == "__main__":
    if len(sys.argv) > 1:
        mode = sys.argv[1].lower()
        host = sys.argv[2] if len(sys.argv) > 2 else DEFAULT_HOST
        port = int(sys.argv[3]) if len(sys.argv) > 3 else DEFAULT_PORT

        if mode == "server":
            run_tcp_server(host, port)
        elif mode == "client":
            interactive_client(host, port)
        else:
            print("Unknown argument. Use 'server', 'client', or run with no args for demo.")
    else:
        run_demo()
