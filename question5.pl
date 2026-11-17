% ====================================================================
% Question 5: Prolog Knowledge Base for Course Eligibility
% ====================================================================

% (a) Logical Facts
% Format: passed(Student, Course).
passed(arun, python).
passed(arun, data_structures).
passed(bala, python).
passed(charan, python).
passed(charan, data_structures).
passed(divya, data_structures).

% (b) Logical Rule: Eligibility for Advanced Programming
eligible_for_advanced_programming(Student) :-
    passed(Student, python),
    passed(Student, data_structures).

% (c) Query to identify all eligible students:
% Run in Prolog listener:
% ?- eligible_for_advanced_programming(Student).
%
% Expected Output:
% Student = arun ;
% Student = charan.
