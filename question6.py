"""
Question 6:
An organization considers employees for promotion based on the following conditions:
- Minimum five years of experience.
- Completion of mandatory training.
- Performance rating of at least 4.

Employee   Experience (Years)   Training          Rating
Ravi       6                    Completed         5
Priya      3                    Completed         4
Kumar      8                    Not Completed     5
Meena      7                    Completed         4

a) Represent the employee information as logical facts.
b) Define a logical rule for promotion eligibility.
c) Write a query to retrieve employees eligible for promotion.

======================================================================
LOGICAL / PROLOG REPRESENTATION:
======================================================================
(a) Facts:
    % employee(Name, ExperienceYears, TrainingStatus, Rating).
    employee(ravi, 6, completed, 5).
    employee(priya, 3, completed, 4).
    employee(kumar, 8, not_completed, 5).
    employee(meena, 7, completed, 4).

(b) Rule:
    eligible_for_promotion(Employee) :-
        employee(Employee, Experience, completed, Rating),
        Experience >= 5,
        Rating >= 4.

(c) Query:
    ?- eligible_for_promotion(Employee).
    Result:
    Employee = ravi ;
    Employee = meena.
======================================================================
"""


# ====================================================================
# PYTHON IMPLEMENTATION OF THE PROMOTION LOGIC SYSTEM
# ====================================================================

# (a) Represent the employee information as logical facts
EMPLOYEE_FACTS = [
    {"name": "Ravi", "experience": 6, "training": "Completed", "rating": 5},
    {"name": "Priya", "experience": 3, "training": "Completed", "rating": 4},
    {"name": "Kumar", "experience": 8, "training": "Not Completed", "rating": 5},
    {"name": "Meena", "experience": 7, "training": "Completed", "rating": 4},
]


# (b) Define a logical rule for promotion eligibility
def eligible_for_promotion(employee: dict) -> bool:
    """
    Logical Rule for promotion eligibility:
    eligible_for_promotion(Employee) :-
        Experience >= 5,
        Training == 'Completed',
        Rating >= 4.
    """
    return (
        employee["experience"] >= 5
        and employee["training"].lower() == "completed"
        and employee["rating"] >= 4
    )


# (c) Query to retrieve employees eligible for promotion
def query_promotable_employees() -> list:
    """
    Query: ?- eligible_for_promotion(Employee).
    Returns list of employees satisfying the promotion rule.
    """
    return [emp for emp in EMPLOYEE_FACTS if eligible_for_promotion(emp)]


def main():
    print("=" * 70)
    print("QUESTION 6: Rule-Based Logic System - Employee Promotion")
    print("=" * 70)

    print("\n--- (a) Representation as Logical Facts ---")
    print("Prolog Predicates: employee(Name, Experience, Training, Rating).")
    print("  employee(ravi, 6, completed, 5).")
    print("  employee(priya, 3, completed, 4).")
    print("  employee(kumar, 8, not_completed, 5).")
    print("  employee(meena, 7, completed, 4).")

    print("\nEmployee Database Records:")
    print(f"  {'Name':10} | {'Experience (Yrs)':18} | {'Training':16} | {'Rating':6}")
    print("  " + "-" * 56)
    for emp in EMPLOYEE_FACTS:
        print(f"  {emp['name']:10} | {emp['experience']:18} | {emp['training']:16} | {emp['rating']:6}")

    print("\n--- (b) Logical Rule Definition ---")
    print("Prolog Rule Definition:")
    print("  eligible_for_promotion(Employee) :-")
    print("      employee(Employee, Experience, completed, Rating),")
    print("      Experience >= 5,")
    print("      Rating >= 4.")
    print("\nConditions:")
    print("  1. Experience >= 5 years")
    print("  2. Training == 'Completed'")
    print("  3. Rating >= 4")

    print("\n--- (c) Query Execution & Evaluation ---")
    print("Query: ?- eligible_for_promotion(Employee).")
    
    promotable = query_promotable_employees()
    print("\nEligible Employees for Promotion:")
    for emp in promotable:
        print(f"  -> {emp['name']:6} : ELIGIBLE")
        print(f"     [Exp: {emp['experience']} yrs (>=5), Training: {emp['training']}, Rating: {emp['rating']} (>=4)]")

    print("\nIneligible Employees Breakdown:")
    for emp in EMPLOYEE_FACTS:
        if emp not in promotable:
            reasons = []
            if emp["experience"] < 5:
                reasons.append(f"Insufficient experience ({emp['experience']} < 5)")
            if emp["training"].lower() != "completed":
                reasons.append(f"Mandatory training {emp['training']}")
            if emp["rating"] < 4:
                reasons.append(f"Rating {emp['rating']} < 4")
            print(f"  -> {emp['name']:6} : NOT ELIGIBLE - Reason: {', '.join(reasons)}")

    print("=" * 70)


if __name__ == "__main__":
    main()
