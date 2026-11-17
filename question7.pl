% ====================================================================
% Question 7: Prolog Knowledge Base for Parking Allocation System
% ====================================================================

% (a) Logical Facts
% Predicate: parking_slot(SlotID, VehicleType, AvailabilityStatus).
parking_slot(s1, car, available).
parking_slot(s2, bike, occupied).
parking_slot(s3, car, available).
parking_slot(s4, bike, available).
parking_slot(s5, car, occupied).

% (b) Logical Rule to identify available parking slots for a vehicle type
available_parking_slot(Slot, VehicleType) :-
    parking_slot(Slot, VehicleType, available).

% (c) Query to retrieve available car parking slots
% Run in Prolog listener:
% ?- available_parking_slot(Slot, car).
%
% Expected Output:
% Slot = s1 ;
% Slot = s3.
