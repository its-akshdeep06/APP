"""
Question 7:
A shopping mall uses a rule-based parking allocation system.
The following parking spaces are available:

Slot   Vehicle Type   Availability
S1     Car            Available
S2     Bike           Occupied
S3     Car            Available
S4     Bike           Available
S5     Car            Occupied

a) Represent the parking slot details using facts.
b) Define a rule to identify available parking slots for a particular vehicle type.
c) Write a query to retrieve available car parking slots.

======================================================================
LOGICAL / PROLOG REPRESENTATION:
======================================================================
(a) Facts:
    % parking_slot(SlotID, VehicleType, AvailabilityStatus).
    parking_slot(s1, car, available).
    parking_slot(s2, bike, occupied).
    parking_slot(s3, car, available).
    parking_slot(s4, bike, available).
    parking_slot(s5, car, occupied).

(b) Rule:
    available_parking_slot(Slot, VehicleType) :-
        parking_slot(Slot, VehicleType, available).

(c) Query:
    ?- available_parking_slot(Slot, car).
    Result:
    Slot = s1 ;
    Slot = s3.
======================================================================
"""


# ====================================================================
# PYTHON IMPLEMENTATION OF THE PARKING ALLOCATION SYSTEM
# ====================================================================

# (a) Represent the parking slot details using facts
PARKING_SLOTS = [
    {"slot": "S1", "vehicle_type": "Car", "availability": "Available"},
    {"slot": "S2", "vehicle_type": "Bike", "availability": "Occupied"},
    {"slot": "S3", "vehicle_type": "Car", "availability": "Available"},
    {"slot": "S4", "vehicle_type": "Bike", "availability": "Available"},
    {"slot": "S5", "vehicle_type": "Car", "availability": "Occupied"},
]


# (b) Define a rule to identify available parking slots for a particular vehicle type
def available_parking_slot(slot_record: dict, requested_type: str) -> bool:
    """
    Logical Rule:
    available_parking_slot(Slot, VehicleType) :-
        parking_slot(Slot, VehicleType, available).
    """
    return (
        slot_record["vehicle_type"].lower() == requested_type.lower()
        and slot_record["availability"].lower() == "available"
    )


# (c) Query engine to retrieve available parking slots for a vehicle type
def query_available_slots(vehicle_type: str) -> list:
    """
    Query: ?- available_parking_slot(Slot, VehicleType).
    """
    return [
        slot["slot"]
        for slot in PARKING_SLOTS
        if available_parking_slot(slot, vehicle_type)
    ]


def main():
    print("=" * 70)
    print("QUESTION 7: Rule-Based Parking Allocation System")
    print("=" * 70)

    print("\n--- (a) Representation of Parking Slots as Facts ---")
    print("Prolog Predicates: parking_slot(SlotID, VehicleType, Status).")
    print("  parking_slot(s1, car, available).")
    print("  parking_slot(s2, bike, occupied).")
    print("  parking_slot(s3, car, available).")
    print("  parking_slot(s4, bike, available).")
    print("  parking_slot(s5, car, occupied).")

    print("\nParking Slot Status Table:")
    print(f"  {'Slot ID':8} | {'Vehicle Type':14} | {'Availability':12}")
    print("  " + "-" * 40)
    for s in PARKING_SLOTS:
        print(f"  {s['slot']:8} | {s['vehicle_type']:14} | {s['availability']:12}")

    print("\n--- (b) Logical Rule Definition ---")
    print("Prolog Rule Definition:")
    print("  available_parking_slot(Slot, VehicleType) :-")
    print("      parking_slot(Slot, VehicleType, available).")
    print("\nRule Description:")
    print("  'A slot is considered available for a vehicle type if and only if")
    print("   the designated vehicle type matches AND the slot status is available.'")

    print("\n--- (c) Query Execution to Retrieve Available Car Parking Slots ---")
    print("Query: ?- available_parking_slot(Slot, car).")

    available_car_slots = query_available_slots("car")
    print(f"\nRetrieved Available Car Parking Slots: {available_car_slots}")
    for slot_id in available_car_slots:
        print(f"  -> Slot {slot_id} is VACANT and ready for Car parking.")

    # Demonstration of queries for other vehicle types
    print("\nAdditional Verification (Bike Query):")
    print("Query: ?- available_parking_slot(Slot, bike).")
    available_bike_slots = query_available_slots("bike")
    print(f"Retrieved Available Bike Parking Slots: {available_bike_slots}")
    for slot_id in available_bike_slots:
        print(f"  -> Slot {slot_id} is VACANT and ready for Bike parking.")

    print("=" * 70)


if __name__ == "__main__":
    main()
