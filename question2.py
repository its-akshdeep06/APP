"""
Question 2:
A company maintains the monthly salaries of its employees.
The company decides to provide a 10% salary increment to employees earning below Rs. 40,000.
a) Develop a program using map() and lambda functions to revise the salaries.
b) Use filter() to identify employees eligible for the increment.
c) Calculate the total salary expenditure after revision.
d) Explain why functional programming is suitable for processing salary records.
"""

from functools import reduce


def main():
    # Dataset: List of tuples (Employee Name, Monthly Salary in Rs.)
    employees = [
        ("Ravi", 35000.0),
        ("Priya", 52000.0),
        ("Kumar", 38000.0),
        ("Meena", 45000.0),
        ("Anil", 28000.0),
        ("Sunita", 60000.0)
    ]

    print("=" * 65)
    print("QUESTION 2: Functional Salary Revision & Expenditure Processing")
    print("=" * 65)
    print("Current Employee Records:")
    for name, salary in employees:
        print(f"  {name:10}: Rs. {salary:,.2f}")

    # -------------------------------------------------------------
    # (b) Use filter() to identify employees eligible for the increment (< 40,000)
    # -------------------------------------------------------------
    eligible_for_increment = list(
        filter(lambda emp: emp[1] < 40000, employees)
    )

    print("\n--- (b) Employees Eligible for Increment (< Rs. 40,000) (filter) ---")
    for name, salary in eligible_for_increment:
        print(f"  {name:10}: Current Salary = Rs. {salary:,.2f}")

    # -------------------------------------------------------------
    # (a) Develop a program using map() and lambda functions to revise the salaries
    #     Employees earning < 40,000 receive a 10% increment (salary * 1.10)
    # -------------------------------------------------------------
    revised_employees = list(
        map(
            lambda emp: (
                emp[0],
                emp[1] * 1.10 if emp[1] < 40000 else emp[1]
            ),
            employees
        )
    )

    print("\n--- (a) Revised Salaries with 10% Hike for Eligible Staff (map) ---")
    for (orig_name, orig_sal), (rev_name, rev_sal) in zip(employees, revised_employees):
        increment_info = f"(+10% hike: +Rs. {rev_sal - orig_sal:,.2f})" if rev_sal > orig_sal else "(No change)"
        print(f"  {rev_name:10}: Rs. {rev_sal:,.2f}  {increment_info}")

    # -------------------------------------------------------------
    # (c) Calculate the total salary expenditure after revision using reduce()
    # -------------------------------------------------------------
    total_expenditure_before = reduce(lambda acc, emp: acc + emp[1], employees, 0.0)
    total_expenditure_after = reduce(lambda acc, emp: acc + emp[1], revised_employees, 0.0)

    print("\n--- (c) Total Salary Expenditure (reduce) ---")
    print(f"  Total Expenditure Before Revision : Rs. {total_expenditure_before:,.2f}")
    print(f"  Total Expenditure After Revision  : Rs. {total_expenditure_after:,.2f}")
    print(f"  Net Additional Cost to Company    : Rs. {total_expenditure_after - total_expenditure_before:,.2f}")

    # -------------------------------------------------------------
    # (d) Explain why functional programming is suitable for processing salary records
    # -------------------------------------------------------------
    print("\n--- (d) Explanation: Why Functional Programming is Suitable ---")
    explanation = """
    Why Functional Programming (FP) is Suitable for Salary Records:
    1. Immutability (Data Integrity):
       In financial applications, modifying records in-place (mutability) is 
       prone to catastrophic side effects or accidental corruption. In FP, data
       structures are immutable; operations produce new datasets while leaving 
       the original payroll history unaltered.
       
    2. Pure Functions & Determinism:
       Pure functions have no side effects and always produce identical outputs 
       for given inputs. This makes salary computations 100% predictable, 
       testable, and verifiable for auditing purposes.
       
    3. Safe Concurrency & Parallelism:
       Because functional operations do not mutate shared state, processing 
       salaries for thousands of employees across distributed cores can happen 
       safely without race conditions or locks.
       
    4. Declarative Pipeline:
       Higher-order functions (filter -> map -> reduce) clearly express the 
       business logic (who qualifies -> how much to adjust -> total budget) 
       concisely without complex loops and state flags.
    """
    print(explanation.strip())
    print("=" * 65)


if __name__ == "__main__":
    main()
