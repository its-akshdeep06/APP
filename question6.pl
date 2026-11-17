% ====================================================================
% Question 6: Prolog Knowledge Base for Employee Promotion
% ====================================================================

% (a) Logical Facts
% Predicate: employee(Name, ExperienceInYears, TrainingStatus, PerformanceRating).
employee(ravi, 6, completed, 5).
employee(priya, 3, completed, 4).
employee(kumar, 8, not_completed, 5).
employee(meena, 7, completed, 4).

% (b) Logical Rule for Promotion Eligibility
% Condition: Minimum 5 years experience, training completed, rating >= 4.
eligible_for_promotion(Employee) :-
    employee(Employee, Experience, completed, Rating),
    Experience >= 5,
    Rating >= 4.

% (c) Query to retrieve employees eligible for promotion
% Run in Prolog listener:
% ?- eligible_for_promotion(Employee).
%
% Expected Output:
% Employee = ravi ;
% Employee = meena.
