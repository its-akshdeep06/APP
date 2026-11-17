"""
Tutorial - 11 (Question 4)
--------------------------
Problem Statement:
An automobile company wants to analyze the motion of a vehicle using its displacement
function s(t) = t^3 - 6t^2 + 9t + 5, where t represents time. Develop a Python
program using the SymPy library to:
1. Determine the velocity and acceleration through symbolic differentiation.
2. Identify the time at which the vehicle is momentarily at rest.
3. Calculate the displacement over a specified time interval using definite integration.
"""

from sympy import symbols, diff, solve, integrate, Eq, pprint


def main():
    print("=" * 70)
    print("TUTORIAL 11 - QUESTION 4: Vehicle Kinematics Analysis with SymPy")
    print("=" * 70)

    # -------------------------------------------------------------
    # 1. Define Symbolic Time Variable and Displacement Function s(t)
    # -------------------------------------------------------------
    t = symbols('t', real=True, nonnegative=True)
    
    # Displacement function s(t)
    s = t**3 - 6*t**2 + 9*t + 5

    print("\n1. Displacement Function s(t):")
    print(f"   s(t) = {s}")

    # -------------------------------------------------------------
    # 2. Determine Velocity and Acceleration via Differentiation
    # -------------------------------------------------------------
    # Velocity v(t) = ds/dt
    v = diff(s, t)
    
    # Acceleration a(t) = dv/dt = d^2s/dt^2
    a = diff(v, t)

    print("\n2. Symbolic Differentiation:")
    print(f"   Velocity     v(t) = ds/dt   = {v}")
    print(f"   Acceleration a(t) = d^2s/dt^2 = {a}")

    # -------------------------------------------------------------
    # 3. Identify Times When Vehicle is Momentarily at Rest (v(t) == 0)
    # -------------------------------------------------------------
    rest_condition = Eq(v, 0)
    rest_times = solve(rest_condition, t)

    print("\n3. Momentarily at Rest Analysis (v(t) = 0):")
    print(f"   Equation: {v} = 0")
    print(f"   Factored: 3*(t - 1)*(t - 3) = 0")
    print(f"   Rest Times (t >= 0): {rest_times} seconds")

    for t_val in rest_times:
        disp_at_rest = s.subs(t, t_val)
        acc_at_rest = a.subs(t, t_val)
        state_nature = "Local Maximum Displacement" if acc_at_rest < 0 else "Local Minimum Displacement"
        print(f"\n   At t = {t_val} s:")
        print(f"     - Displacement s({t_val})   = {disp_at_rest} m")
        print(f"     - Velocity     v({t_val})   = {v.subs(t, t_val)} m/s (At Rest)")
        print(f"     - Acceleration a({t_val})   = {acc_at_rest} m/s^2 ({state_nature})")

    # -------------------------------------------------------------
    # 4. Calculate Displacement via Definite Integration
    # -------------------------------------------------------------
    # Displacement over interval [t1, t2]:
    # Delta s = integral from t1 to t2 of v(t) dt = s(t2) - s(t1)
    
    t_start, t_end = 0, 4
    print(f"\n4. Definite Integration over Time Interval [{t_start}, {t_end}] seconds:")

    displacement_integral = integrate(v, (t, t_start, t_end))
    s_end = s.subs(t, t_end)
    s_start = s.subs(t, t_start)
    displacement_diff = s_end - s_start

    print(f"   Integral : Integral_{t_start}^{t_end} ({v}) dt")
    print(f"   Calculated Net Displacement : {displacement_integral} m")
    print(f"   Verification via s({t_end}) - s({t_start}) : {s_end} - {s_start} = {displacement_diff} m")

    # Another standard interval: between the two rest points [1, 3]
    t1, t2 = 1, 3
    disp_between_rest = integrate(v, (t, t1, t2))
    print(f"\n   Displacement between rest points [{t1}, {t2}] s:")
    print(f"     Integral_{t1}^{t2} ({v}) dt = {disp_between_rest} m")
    print(f"     (Vehicle moves in reverse direction from s(1)=9m to s(3)=5m)")

    print("=" * 70)


if __name__ == "__main__":
    main()
