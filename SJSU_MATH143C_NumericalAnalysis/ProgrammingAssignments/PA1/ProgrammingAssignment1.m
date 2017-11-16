% Computes the value of 21^1/3 by 
%   4 unique fixed point iteration methods.

% User input
prompt = 'Enter p0: ';
p0 = input(prompt);

% Solve A first
problemNum = 'A';
funct = @problemA;

% Solves all 4 of the problems
endSolve = false;
while (~endSolve)
    
    % Reset the initial variables
    previousP = p0;
    pCount = 0;
    stop = false;
    
    % Solve the current problem
    while (~stop)
        currentP = funct(previousP);
        pCount = pCount + 1;
        % End if the first 6 sig figs are equal
        if (strcmp(num2str(currentP,6),num2str(previousP,6)))
            stop = true;  
        end
        previousP = currentP;
    end
    disp(strcat(problemNum,') p',int2str(pCount),': ',num2str(currentP,5))) 
    
    % Set funct to the next problem
    if (strcmp(problemNum,'A')) 
        funct = @problemB;
        problemNum = 'B';
    elseif (strcmp(problemNum,'B')) 
        funct = @problemC;
        problemNum = 'C';
    elseif (strcmp(problemNum,'C')) 
        funct = @problemD;
        problemNum = 'D';
    else
        endSolve = true;
    end
    
end