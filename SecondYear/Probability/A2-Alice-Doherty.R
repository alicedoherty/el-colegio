# Question 1
# Running time approx 3mins 30secs
# answer ~ 14.7

# Function that returns the number of rolls required to get each face at least once
# Works by creating a numeric vector of size 6 and putting a 1 in the position rolled
# Once the sum of the vector 6 - all faces have been rolled and function returns count
dice_rolls <- function() {
  faces <- 1:6
  count <- 0
  dice_faces_rolled <- numeric(6)
  
  while (sum(dice_faces_rolled) < 6) {
    i <- sample(faces, 1)
    dice_faces_rolled[i] <- 1
    count <- count + 1
  }
  return (count)
}

# Runs dice_rolls 10,000 times and returns the average number of rolls required
dice_rolls_experiment <- function() {
  n <- 10000
  rolls <- replicate(n, dice_rolls())
  result <- sum(rolls)/n
  return (result)
}

# Calculates the mean probability of the results for 200 iterations of the experiment
iterations <- 200
results <- replicate(iterations, dice_rolls_experiment())
probability <- sum(results)/iterations
probability

# Question 2
# answer for each k ~ 0.37

# Randomly generates an order of k cards from 1:k and randomly generates k guesses from 1:k
# Compares guesses and cards and only if NONE match, returns true
guess_check <- function(k) {
  cards <- sample(1:k, k, replace=FALSE)
  guess <- sample(1:k, k, replace=FALSE)
  return (all(cards!=guess))
}

# Runs guess_check 10,000 times and returns the probability from 10,000 samples
card_experiment <- function(k) {
  n <- 10000
  guess_results <- replicate(n, guess_check(k))
  result <- sum(guess_results)/n
  return(result)
}

# Calculates the mean probability of the results for 200 iterations of the experiment
experiment <- function(k) {
  iterations <- 200
  results <- replicate(iterations, card_experiment(k))
  probability <- sum(results)/iterations
  return(probability)
}

# Iterates k from 6 - 10, runs the experiment, and stores the result in a vector
for(k in 6:10) {
  answers[k-5] <- experiment(k)
}

# Prints out results for k = 6, 7, 8, 9, 10
answers

# Plots result of probabilities vs k
plot(6:10, answers, type="o", main = "Lab 2, Q2", xlab="k", ylab="Probability")