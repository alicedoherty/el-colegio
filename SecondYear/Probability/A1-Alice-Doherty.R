# Running time approx 1min
# answer ~ 0.608

install.packages("numbers")
library(numbers)

# Collects results of testing 10,000 random pairs of numbers (between 0 and integer.max) for being coprime
# Returns the probability from the 10,000 samples
is_coprime <- function() {
  number_of_pairs <- 10000
  result <- replicate(number_of_pairs, coprime(sample(0:.Machine$integer.max, 1), sample(0:.Machine$integer.max, 1)))
  return (sum(result)/number_of_pairs)
}

# Calculates the mean probability of the results for 200 iterations of the experiment
iterations <- 200
results <- replicate(iterations, is_coprime())
mean_probability <- sum(results)/iterations
mean_probability