"""
Tutorial - 11 (Question 3)
--------------------------
Problem Statement:
A manufacturing company needs to simplify mathematical expressions used in
calculating production costs. Develop a Python program using the SymPy library
to define symbolic variables, expand and factorize algebraic expressions,
simplify the resulting expressions, and solve a quadratic equation to determine
the unknown production quantity.
"""

from sympy import symbols, expand, factor, simplify, solve, Eq, pprint


def main():
    print("=" * 70)
    print("TUTORIAL 11 - QUESTION 3: Symbolic Mathematics for Production Costs")
    print("=" * 70)

    # -------------------------------------------------------------
    # 1. Define Symbolic Variables
    # -------------------------------------------------------------
    # 'q' represents the unknown production quantity (units produced)
    # 'c' represents unit base cost
    q, c = symbols('q c', positive=True, real=True)
    print("\n1. Symbolic Variables Defined:")
    print(f"   q : Production Quantity (units)")
    print(f"   c : Unit Base Cost factor")

    # -------------------------------------------------------------
    # 2. Expand Algebraic Expressions
    # -------------------------------------------------------------
    # Consider a total manufacturing cost model consisting of combined factor terms:
    # e.g., Base setup and scaling factors: (2q + 5)*(3q + 20)
    raw_cost_expression = (2 * q + 5) * (3 * q + 20)
    expanded_expr = expand(raw_cost_expression)

    print("\n2. Expanding Algebraic Cost Expression:")
    print(f"   Unexpanded Expression : {raw_cost_expression}")
    print(f"   Expanded Expression   : {expanded_expr}")

    # Another example: quadratic cost with overhead factor (q + 15)**2
    overhead_expr = (q + 15)**2
    expanded_overhead = expand(overhead_expr)
    print(f"   Overhead (q + 15)^2   : {expanded_overhead}")

    # -------------------------------------------------------------
    # 3. Factorize Algebraic Expressions
    # -------------------------------------------------------------
    # Factorizing a polynomial cost expression: 6*q**2 + 55*q + 100
    poly_expr = 6 * q**2 + 55 * q + 100
    factorized_expr = factor(poly_expr)

    print("\n3. Factorizing Algebraic Cost Expression:")
    print(f"   Polynomial to Factor  : {poly_expr}")
    print(f"   Factorized Result     : {factorized_expr}")

    # -------------------------------------------------------------
    # 4. Simplify Resulting Expressions
    # -------------------------------------------------------------
    # A complex rational expression representing average cost or efficiency index:
    # Expression: (2*q**2 + 10*q) / (q**2 + 5*q) + (q**2 - 9) / (q - 3)
    complex_cost_ratio = (2 * q**2 + 10 * q) / (q**2 + 5 * q) + (q**2 - 9) / (q - 3)
    simplified_expr = simplify(complex_cost_ratio)

    print("\n4. Simplifying Rational Production Cost Expression:")
    print(f"   Complex Expression    : {complex_cost_ratio}")
    print(f"   Simplified Form       : {simplified_expr}")

    # -------------------------------------------------------------
    # 5. Solve Quadratic Equation to Find Production Quantity (q)
    # -------------------------------------------------------------
    # Suppose total manufacturing cost C(q) is given by:
    #   C(q) = 2*q^2 + 20*q + 300
    # The budget/target revenue is Rs. 1500.
    # Therefore: 2*q^2 + 20*q + 300 = 1500
    # Rearranging: 2*q^2 + 20*q - 1200 = 0
    # Dividing by 2: q^2 + 10*q - 600 = 0
    # Factorizes to (q + 30)(q - 20) = 0 => q = 20 (since q > 0)
    
    cost_eq = Eq(2 * q**2 + 20 * q + 300, 1500)
    print("\n5. Solving Quadratic Cost Equation for Production Quantity:")
    print(f"   Production Cost Equation: {cost_eq}")
    
    # Solve symbolically
    solutions = solve(cost_eq, q)
    print(f"   Symbolic Solutions: {solutions}")

    # Filter for valid physical production quantity (quantity must be positive)
    valid_quantities = [sol for sol in solutions if sol.is_real and sol > 0]

    print("\nProduction Engineering Decision:")
    for sol in solutions:
        if sol in valid_quantities:
            print(f"   -> q = {sol} units [VALID: Feasible positive production quantity]")
        else:
            print(f"   -> q = {sol} units [DISCARDED: Non-physical negative quantity]")

    print(f"\nTarget Production Quantity required to meet cost target: {valid_quantities[0]} units")
    print("=" * 70)


if __name__ == "__main__":
    main()
