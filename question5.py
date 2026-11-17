"""
Question 5:
A university permits students to register for an advanced programming course only if they have passed Python Programming and Data Structures.
The following facts are available:
Student   Python   Data Structures
Arun      Pass     Pass
Bala      Pass     Fail
Charan    Pass     Pass
Divya     Fail     Pass

a) Represent the student results using logical facts.
b) Define a rule to determine eligibility for the advanced programming course.
c) Write a query to identify all eligible students.

======================================================================
LOGICAL / PROLOG REPRESENTATION:
======================================================================
(a) Facts:
    passed(arun, python).
    passed(arun, data_structures).
    passed(bala, python).
    passed(charan, python).
    passed(charan, data_structures).
    passed(divya, data_structures).

(b) Rule:
    eligible_for_advanced_programming(Student) :-
        passed(Student, python),
        passed(Student, data_structures).

(c) Query:
    ?- eligible_for_advanced_programming(Student).
    Result:
    Student = arun ;
    Student = charan.
======================================================================
"""


# ====================================================================
# PYTHON IMPLEMENTATION OF THE LOGIC SYSTEM
# ====================================================================

# (a) Representing student results using logical facts (Knowledge Base)
FACTS = [
    {"student": "Arun", "course": "Python", "status": "Pass"},
    {"student": "Arun", "course": "Data Structures", "status": "Pass"},
    {"student": "Bala", "course": "Python", "status": "Pass"},
    {"student": "Bala", "course": "Data Structures", "status": "Fail"},
    {"student": "Charan", "course": "Python", "status": "Pass"},
    {"student": "Charan", "course": "Data Structures", "status": "Pass"},
    {"student": "Divya", "course": "Python", "status": "Fail"},
    {"student": "Divya", "course": "Data Structures", "status": "Pass"},
]

# Helper predicate: passed(student, course)
def passed(student: str, course: str) -> bool:
    """Predicate: checks if student has 'Pass' fact for the given course."""
    return any(
        fact["student"].lower() == student.lower()
        and fact["course"].lower() == course.lower()
        and fact["status"].lower() == "pass"
        for fact in FACTS
    )


# (b) Define a rule to determine eligibility for the advanced programming course
def eligible_for_advanced_programming(student: str) -> bool:
    """
    Logical Rule:
    eligible_for_advanced_programming(Student) :-
        passed(Student, 'Python'),
        passed(Student, 'Data Structures').
    """
    return passed(student, "Python") and passed(student, "Data Structures")


# (c) Query engine to identify all eligible students
def query_eligible_students() -> list:
    """
    Query: ?- eligible_for_advanced_programming(Student).
    Finds all unique students and returns those satisfying the rule.
    """
    all_students = sorted(list({fact["student"] for fact in FACTS}))
    return [student for student in all_students if eligible_for_advanced_programming(student)]


def main():
    print("=" * 65)
    print("QUESTION 5: Rule-Based Logic System - Course Registration")
    print("=" * 65)

    print("\n--- (a) Logical Facts Representation ---")
    print("Prolog / First-Order Logic Syntax:")
    print("  passed(arun, python).")
    print("  passed(arun, data_structures).")
    print("  passed(bala, python).")
    print("  passed(charan, python).")
    print("  passed(charan, data_structures).")
    print("  passed(divya, data_structures).")

    print("\nTabular Knowledge Base:")
    for fact in FACTS:
        print(f"  Fact: {fact['student']:8} | Course: {fact['course']:16} | Status: {fact['status']}")

    print("\n--- (b) Logical Rule Definition ---")
    print("Prolog Rule:")
    print("  eligible_for_advanced_programming(Student) :-")
    print("      passed(Student, python),")
    print("      passed(Student, data_structures).")
    print("\nEnglish:")
    print("  'A student is eligible for Advanced Programming if and only if")
    print("   they have passed Python AND they have passed Data Structures.'")

    print("\n--- (c) Query & Results ---")
    print("Query: ?- eligible_for_advanced_programming(Student).")
    
    eligible = query_eligible_students()
    print("\nQuery Evaluation Results:")
    for student in eligible:
        print(f"  -> {student} is ELIGIBLE for Advanced Programming Course.")
        print(f"     [Verified: passed({student}, Python)=True, passed({student}, Data Structures)=True]")

    # Check non-eligible students
    all_students = sorted(list({fact["student"] for fact in FACTS}))
    non_eligible = [s for s in all_students if s not in eligible]
    print("\nIneligible Students:")
    for student in non_eligible:
        py_status = "Pass" if passed(student, "Python") else "Fail"
        ds_status = "Pass" if passed(student, "Data Structures") else "Fail"
        print(f"  -> {student} is NOT eligible (Python: {py_status}, Data Structures: {ds_status})")
    
    print("=" * 65)


if __name__ == "__main__":
    main()
