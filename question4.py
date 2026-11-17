"""
Question 4:
A smart electricity meter records the daily electricity consumption of a household.
a) Use filter() to identify days when consumption exceeded 10 units.
b) Use map() to calculate the electricity cost for each day at Rs. 6 per unit.
c) Use reduce() to determine the total electricity consumption.
d) Develop a pure function to calculate the electricity bill without changing the original consumption data.
"""

from functools import reduce


# -------------------------------------------------------------
# (d) Develop a pure function to calculate the electricity bill
#     without changing the original consumption data
# -------------------------------------------------------------
def calculate_electricity_bill(readings: list, rate_per_unit: float = 6.0) -> dict:
    """
    Pure Function to calculate the total electricity bill and breakdown.
    
    Guarantees:
    - Pure and side-effect free: does NOT mutate `readings`.
    - Input parameters are treated as read-only.
    - Computes total units and total cost deterministically.
    
    Returns a dictionary containing:
      - 'total_units': float
      - 'rate_per_unit': float
      - 'total_bill': float
    """
    # Non-destructive aggregation using reduce on units
    total_units = reduce(lambda acc, item: acc + item[1], readings, 0.0)
    total_bill = total_units * rate_per_unit
    
    return {
        "total_units": round(total_units, 2),
        "rate_per_unit": rate_per_unit,
        "total_bill": round(total_bill, 2)
    }


def main():
    # Dataset: Daily electricity consumption in units (kWh)
    # List of tuples (Day, Units Consumed)
    daily_consumption = [
        ("Monday", 8.5),
        ("Tuesday", 12.0),
        ("Wednesday", 15.5),
        ("Thursday", 9.0),
        ("Friday", 14.0),
        ("Saturday", 7.5),
        ("Sunday", 11.0)
    ]

    # Preserved snapshot to verify immutability later
    original_snapshot = list(daily_consumption)

    print("=" * 65)
    print("QUESTION 4: Smart Electricity Meter Consumption Processing")
    print("=" * 65)
    print("Weekly Smart Meter Readings:")
    for day, units in daily_consumption:
        print(f"  {day:12}: {units:4.1f} units")

    # -------------------------------------------------------------
    # (a) Use filter() to identify days when consumption exceeded 10 units
    # -------------------------------------------------------------
    high_consumption_days = list(
        filter(lambda record: record[1] > 10.0, daily_consumption)
    )

    print("\n--- (a) Days Exceeding 10 Units (filter) ---")
    for day, units in high_consumption_days:
        print(f"  {day:12}: {units:4.1f} units (High Usage)")

    # -------------------------------------------------------------
    # (b) Use map() to calculate electricity cost for each day at Rs. 6/unit
    # -------------------------------------------------------------
    RATE_PER_UNIT = 6.0
    daily_costs = list(
        map(lambda record: (record[0], record[1], record[1] * RATE_PER_UNIT), daily_consumption)
    )

    print(f"\n--- (b) Daily Electricity Cost @ Rs. {RATE_PER_UNIT:.2f} per unit (map) ---")
    for day, units, cost in daily_costs:
        print(f"  {day:12}: {units:4.1f} units -> Rs. {cost:6.2f}")

    # -------------------------------------------------------------
    # (c) Use reduce() to determine total electricity consumption
    # -------------------------------------------------------------
    total_units = reduce(
        lambda acc, record: acc + record[1],
        daily_consumption,
        0.0
    )

    print("\n--- (c) Total Electricity Consumption (reduce) ---")
    print(f"  Total units consumed for the week: {total_units:.2f} units")

    # -------------------------------------------------------------
    # (d) Demonstration of the pure function & immutability verification
    # -------------------------------------------------------------
    bill_summary = calculate_electricity_bill(daily_consumption, rate_per_unit=6.0)

    print("\n--- (d) Electricity Bill via Pure Function ---")
    print(f"  Calculated Total Units : {bill_summary['total_units']} units")
    print(f"  Applied Tariff Rate    : Rs. {bill_summary['rate_per_unit']:.2f} / unit")
    print(f"  Grand Total Bill Amount: Rs. {bill_summary['total_bill']:,.2f}")

    # Immutability Check
    is_unchanged = (daily_consumption == original_snapshot)
    print(f"\n  Original data unchanged? {is_unchanged}")
    print("=" * 65)


if __name__ == "__main__":
    main()
