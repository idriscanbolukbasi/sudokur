## Sudokur
### What is Sudokur?
Sudokur is a basic "Sudoku Puzzle Generator & Solver" and created as homework of Data Structures and Algorithms. The main purpose of the homework was to understand what is "Brute-force approach" and how we can use it and in which algorithms should we use recursive?
### Idea that lies behind on Sudokur
#### Generating puzzles
Generating puzzles from zero requires generator to check whether each generated puzzle is solvable or not and that increases big-O of the program. Instead of generating puzzles from zero, we use a template sudoku grid (puzzle/board) (9x9) which is fully solved to make new sudoku grid which is fully different than the template one. But how could it even be possible? The answer is actually pretty simple, we now that each sudoku grid is unique, and if we apply steps below without broken the basics and rules of sudoku we'll be able to achieve a new table with using template one:
1) We can get 2 random index values which the first one is in the range [0,4] and the second is [5,8] and we can get 2 different values from the first row of the table. Therefore we can change first value to second by starting the first row, thus we can get different table from the template one.
2) We'll use the same logic as we use while we solving sudoku in the real life, remember the 3x3 grids located in 9x9 sudoku grid. What if we shuffle rows, first row and second row do we still get a different table than the tempalate one? Yes, we get.
3) Also we can shuffle columns of the grid as we shuffle the rows but i.e., we can only swap first which is [0] column of the grid either second which is [1] column of the grid or third [2] column of the grid.
The implementation of the all steps that we've mentioned above is not too hard to done. We make all them happen randomly, hence we achieve to create a new sudoku grid by using template one.
But still, we got a problem to deal with. We mentioned how we can get a new sudoku table from the template one for now but we never have mentioned about how we can generate a puzzle with using a fully solved grid. The main idea of generating puzzles is roughly, like every sudoku table is unique on the other hand most of the puzzles are symmetric actually. So, how we can use it to generate puzzles? What if we use this information to generate puzzles? To generate puzzles from fully solved grid we should hide cells but according to what? We should hide cells with respect to X = Y symmetric.
If we choose to hide a cell that has [2,2] coordinates than we should hide the cell that has [6,6] coordinates too. That's the whole story about hiding cells of the grid. We can easily make them happen randomly.
We actually added difficulty options which are optional. Implementation of them is really simple. Even someone who doesn't understand anything about programming can understand of them by just looking at their enum class.
#### Solving puzzles
The main approach that we used while we solving puzzles is quite similar to the program does. We use brute-force approach to solve sudoku puzzles, but what is it and how it is works? The algorithm of brute-force is too simple actually easy simple to understand from as it's named. We just simply look for empty cells of the puzzle and when we found a empty cell, try each of the values of the sudoku that can be fit in here. Firstly, we check is that value can be placed into this cell according to sudoku rules. Secondly, we check (n+1)th empty cell can be filled with using this value that we placed into. If it is not, we'll backtrack and continue where we left. Of course we have base case since we'll be iterating cell to cell to decide when the whole process is done.
#### Cryptology of the program
Don't worry, only cryptology that we done in the program is just simply changing the sudoku values:
1, 2, 3, 4, 5, 6, 7, 8, 9 to
C, N, G, B, I, M, 2, 1, 3 which is actually represents CENG -> (Computer Engineering) BIM213 -> Code of the lesson
So if you want to use it program by just change values C, N, G, B, I, M, 2, 1, 3 to 1, 2, 3, 4, 5, 6, 7, 8, 9 again.
### Usage
Just clone the repository, than go to the direction of cloned repository and write:
`mvn clean package`
After the build is done, just simply write `java -jar target\sudokur.jar` to use Sudokur.
![Image of Sudokur](https://i.imgur.com/B1oUjay.png)
