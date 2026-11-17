"""
Tutorial - 11 (Question 5)
--------------------------
Problem Statement:
A digital security system validates binary access codes before granting access to users.
The system accepts only binary strings that end with "01". Construct a DFA to recognize
valid access codes and develop a Python program using the Automata library to implement
the DFA. Test the automaton with different binary strings and display whether each string
is accepted or rejected.
"""

from automata.fa.dfa import DFA


def display_dfa_specification():
    """Prints the theoretical 5-tuple definition of the DFA."""
    spec = """
======================================================================
DFA FORMAL SPECIFICATION (M = (Q, Sigma, delta, q0, F))
======================================================================
Language: L = { w in {0, 1}* | w ends with '01' }

1. States (Q)              : {q0, q1, q2}
   - q0 : Initial state / last symbol was '1' or start of string
   - q1 : Matched prefix '0' (potential start of suffix '01')
   - q2 : Final state (string currently ends with '01')

2. Alphabet (Sigma)        : {'0', '1'}

3. Start State (q0)        : 'q0'

4. Final / Accept States (F): {'q2'}

5. Transition Function (delta):
   +---------------+---------------+---------------+
   | Current State | Input '0'     | Input '1'     |
   +---------------+---------------+---------------+
   | q0 (Start)    | q1            | q0            |
   | q1            | q1            | q2 (Accept)   |
   | q2 (Accept)   | q1            | q0            |
   +---------------+---------------+---------------+
======================================================================
"""
    print(spec.strip())


def build_dfa() -> DFA:
    """Constructs and returns the DFA using automata-lib."""
    return DFA(
        states={'q0', 'q1', 'q2'},
        input_symbols={'0', '1'},
        transitions={
            'q0': {'0': 'q1', '1': 'q0'},
            'q1': {'0': 'q1', '1': 'q2'},
            'q2': {'0': 'q1', '1': 'q0'}
        },
        initial_state='q0',
        final_states={'q2'}
    )


def trace_execution(dfa: DFA, input_str: str) -> list:
    """Computes the step-by-step state progression for an input string."""
    current_state = dfa.initial_state
    trace = [current_state]
    for char in input_str:
        current_state = dfa.transitions[current_state][char]
        trace.append(current_state)
    return trace


def main():
    print("=" * 70)
    print("TUTORIAL 11 - QUESTION 5: DFA Implementation for Access Code Validation")
    print("=" * 70)

    display_dfa_specification()

    # Construct DFA
    dfa = build_dfa()

    # Comprehensive test suite of binary access codes
    test_cases = [
        "01",             # Shortest valid string
        "001",            # Valid with leading zeros
        "101",            # Valid
        "11001",          # Valid
        "10101",          # Valid (multiple '01' patterns)
        "111101",         # Valid
        "",               # Invalid (empty string)
        "0",              # Invalid (ends with '0')
        "1",              # Invalid (ends with '1')
        "00",             # Invalid (ends with '00')
        "10",             # Invalid (ends with '10')
        "11",             # Invalid (ends with '11')
        "010",            # Invalid (ends with '10')
        "1011",           # Invalid (ends with '11')
        "110010"          # Invalid (ends with '0')
    ]

    print("\n\n" + "=" * 70)
    print("TESTING ACCESS CODES AGAINST DFA")
    print("=" * 70)
    print(f"{'Input Code':12} | {'State Trace':25} | {'Final State':11} | {'Access Decision'}")
    print("-" * 70)

    for code in test_cases:
        trace = trace_execution(dfa, code)
        final_st = trace[-1]
        is_accepted = dfa.accepts_input(code)
        
        trace_str = " -> ".join(trace)
        if len(trace_str) > 24:
            trace_str = trace_str[:21] + "..."

        status_str = "[ACCEPTED] - ACCESS GRANTED" if is_accepted else "[REJECTED] - ACCESS DENIED"
        display_code = f"'{code}'" if code else "''(empty)"
        print(f"{display_code:12} | {trace_str:25} | {final_st:11} | {status_str}")

    print("=" * 70)


if __name__ == "__main__":
    main()
