"""
Tutorial - 11 (Question 6)
--------------------------
Problem Statement:
A text processing system is designed to identify strings over the alphabet that
contain the substring "ab". Construct an NFA to recognize such strings and
develop a Python program using the Automata library to implement the NFA.
Test the automaton with different input strings and display whether each string
is accepted or rejected.
"""

from automata.fa.nfa import NFA


def display_nfa_specification():
    """Prints the theoretical 5-tuple definition of the NFA."""
    spec = """
======================================================================
NFA FORMAL SPECIFICATION (M = (Q, Sigma, delta, q0, F))
======================================================================
Language: L = { w in {a, b}* | w contains the substring 'ab' }
Regular Expression: (a | b)* ab (a | b)*

1. States (Q)              : {q0, q1, q2}
   - q0 : Start state; loops over prefix symbols until substring 'ab' begins
   - q1 : Has just read 'a', non-deterministically guessing start of 'ab'
   - q2 : Final state; substring 'ab' has been detected; loops on all remaining symbols

2. Alphabet (Sigma)        : {'a', 'b'}

3. Start State (q0)        : 'q0'

4. Final / Accept States (F): {'q2'}

5. Transition Function (delta):
   +---------------+---------------+---------------+
   | Current State | Input 'a'     | Input 'b'     |
   +---------------+---------------+---------------+
   | q0 (Start)    | {q0, q1}      | {q0}          |
   | q1            | {}            | {q2} (Accept) |
   | q2 (Accept)   | {q2}          | {q2}          |
   +---------------+---------------+---------------+
======================================================================
"""
    print(spec.strip())


def build_nfa() -> NFA:
    """Constructs and returns the NFA using automata-lib."""
    return NFA(
        states={'q0', 'q1', 'q2'},
        input_symbols={'a', 'b'},
        transitions={
            'q0': {'a': {'q0', 'q1'}, 'b': {'q0'}},
            'q1': {'b': {'q2'}},
            'q2': {'a': {'q2'}, 'b': {'q2'}}
        },
        initial_state='q0',
        final_states={'q2'}
    )


def trace_nfa_step_by_step(nfa: NFA, input_str: str):
    """
    Simulates subset construction to trace active set of states
    after consuming each input symbol.
    """
    current_states = {nfa.initial_state}
    history = [set(current_states)]

    for symbol in input_str:
        next_states = set()
        for state in current_states:
            if symbol in nfa.transitions.get(state, {}):
                next_states.update(nfa.transitions[state][symbol])
        current_states = next_states
        history.append(set(current_states))

    # Accepted if any state in final active set is in nfa.final_states
    is_accepted = bool(current_states.intersection(nfa.final_states))
    return history, is_accepted


def main():
    print("=" * 70)
    print("TUTORIAL 11 - QUESTION 6: NFA Implementation for Substring 'ab'")
    print("=" * 70)

    display_nfa_specification()

    # Construct NFA
    nfa = build_nfa()

    # Comprehensive test suite of input strings
    test_cases = [
        "ab",             # Minimal valid substring
        "aab",            # Starts with multiple 'a's, then 'ab'
        "bab",            # Starts with 'b', then 'ab'
        "baba",           # Contains 'ab' in the middle
        "abaab",          # Contains multiple 'ab's
        "bbabb",          # Contains 'ab' surrounded by 'b's
        "aaaaab",         # 'ab' at the end of 'a' chain
        "ababba",         # 'ab' at prefix and middle
        "",               # Invalid: empty string
        "a",              # Invalid: single 'a'
        "b",              # Invalid: single 'b'
        "ba",             # Invalid: 'ba' is reverse of 'ab'
        "bba",            # Invalid: only 'ba'
        "bbbaaa",         # Invalid: all 'b's followed by all 'a's (no 'ab')
        "aaaa",           # Invalid: only 'a's
        "bbbb"            # Invalid: only 'b's
    ]

    print("\n\n" + "=" * 70)
    print("TESTING INPUT STRINGS AGAINST NFA")
    print("=" * 70)
    print(f"{'Input String':14} | {'Active State Sets History':27} | {'Decision'}")
    print("-" * 70)

    for text in test_cases:
        history, is_accepted = trace_nfa_step_by_step(nfa, text)
        verified_by_lib = nfa.accepts_input(text)

        # Format history of sets for compact display
        formatted_sets = " -> ".join("{" + ",".join(sorted(s)) + "}" for s in history)
        if len(formatted_sets) > 26:
            formatted_sets = formatted_sets[:23] + "..."

        status_str = "[ACCEPTED] - Contains 'ab'" if is_accepted else "[REJECTED] - No 'ab'"
        display_text = f"'{text}'" if text else "''(empty)"
        print(f"{display_text:14} | {formatted_sets:27} | {status_str}")

    print("=" * 70)


if __name__ == "__main__":
    main()
