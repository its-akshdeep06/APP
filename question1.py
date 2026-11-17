"""
Question 1:
A college maintains the marks obtained by students in a programming examination.
Develop a Python program using functional programming concepts to:
a) Identify students who scored 50 marks or above using filter().
b) Award 5 grace marks to every student using map(), ensuring that the maximum mark does not exceed 100.
c) Calculate the total marks using reduce().
d) Explain how lambda functions simplify the implementation.
"""

from functools import reduce


def main():
    # Dataset: List of tuples (Student Name, Marks)
    students = [
        ("Arun", 48),
        ("Bala", 75),
        ("Charan", 98),
        ("Divya", 35),
        ("Eswar", 50),
        ("Fathima", 92)
    ]

    print("=" * 60)
    print("QUESTION 1: Functional Programming in Student Marks Processing")
    print("=" * 60)
    print("Initial Student Records:")
    for name, mark in students:
        print(f"  {name:10}: {mark} marks")

    # -------------------------------------------------------------
    # (a) Identify students who scored 50 marks or above using filter()
    # -------------------------------------------------------------
    scored_50_or_above = list(filter(lambda s: s[1] >= 50, students))

    print("\n--- (a) Students scoring 50 marks or above (filter) ---")
    for name, mark in scored_50_or_above:
        print(f"  {name:10}: {mark} marks")

    # -------------------------------------------------------------
    # (b) Award 5 grace marks using map(), maximum not exceeding 100
    # -------------------------------------------------------------
    students_with_grace = list(
        map(lambda s: (s[0], min(s[1] + 5, 100)), students)
    )

    print("\n--- (b) Marks after awarding 5 grace marks (max 100) (map) ---")
    for name, mark in students_with_grace:
        print(f"  {name:10}: {mark} marks")

    # -------------------------------------------------------------
    # (c) Calculate the total marks using reduce()
    # -------------------------------------------------------------
    # Total marks before grace
    total_original_marks = reduce(lambda acc, s: acc + s[1], students, 0)
    # Total marks after grace
    total_revised_marks = reduce(lambda acc, s: acc + s[1], students_with_grace, 0)

    print("\n--- (c) Total Marks Calculation (reduce) ---")
    print(f"  Total marks (Original) : {total_original_marks}")
    print(f"  Total marks (With Grace): {total_revised_marks}")

    # -------------------------------------------------------------
    # (d) Explanation of how lambda functions simplify the implementation
    # -------------------------------------------------------------
    print("\n--- (d) Explanation: Role of Lambda Functions ---")
    explanation = """
    How Lambda Functions Simplify the Implementation:
    1. Conciseness & Inline Definition:
       Instead of defining separate named functions using `def` for every simple 
       condition or transformation (e.g., def is_passed(s): return s[1] >= 50),
       lambda functions allow writing anonymous, one-line functions right where 
       they are needed.
       
    2. Elimination of Boilerplate Code:
       Lambda reduces function overhead and keeps the codebase clean, especially 
       when passing throwaway helper functions into higher-order functions like 
       filter(), map(), and reduce().
       
    3. Enhanced Readability:
       The transformation logic is placed directly inside the function call,
       making the pipeline logic immediately visible to the reader without 
       jumping across function definitions.
    """
    print(explanation.strip())
    print("=" * 60)


if __name__ == "__main__":
    main()
