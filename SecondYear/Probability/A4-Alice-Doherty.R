#
# Question 1
# Answer ~ 0.875
#

customer_experiment <- function() {
  number_of_samples <- 10000
  exp_lambda <- 1 
  beta <- 1
  k <- 2
  
  # Draws 10,000 random numbers from exponential distribution
  exp_data <- rexp(number_of_samples, exp_lambda)
  
  # Find Poisson lambdas by setting t to numbers generated from exponential distribution
  pois_lambda <- beta * exp_data
  
  # Draws 10,000 random numbers from Poisson distribution
  pois_data <- rpois(number_of_samples, pois_lambda)
  
  # Find cumulative probability, P(X<=2)
  # (Could use: p <- ppois(k, pois_lambda))
  count <- sum(pois_data <= k)
  p <- count/n
  
  return(p)
}

# Calculate mean of results for 200 iterations
iterations <- 200
customer_results <- replicate(iterations, customer_experiment())
customer_probability <- sum(customer_results)/iterations
customer_probability



#
# Question 2
# E[Y] versus a gives a positive linear graph
# Answer ~ 0.375, 0.750, 1.125, 1.500, 1.875
# 

generate_uniform <- function(a) {
  n <- 10000
  
  # Draws 10,000 random numbers from uniform distribution (X)
  uni_data <- runif(n, min = 0, max = a)
  
  return (uni_data)
}

uniform_experiment <- function(a) {
  uni_data <- generate_uniform(a)
  n <- 10000
  
  # Initialise vector to store Y values
  y_values <- numeric(n)
  
  # Iterate through all numbers (X) generated and set corresponding
  # Y to correct value depending on if X < a/2 || X >= a/2
  for(i in 1:n) {
    if(uni_data[i] < a/2) {
      y_values[i] <- uni_data[i]
    }
    else {
      y_values[i] <- a/2
    }
  }
  
  # Return E[Y]
  return (mean(y_values))   
}

# Calculate mean of results of 200 iterations
experiment <- function(a) {
  iterations <- 200
  results <- replicate(iterations, uniform_experiment(a))
  probability <- sum(results/iterations)
  return(probability)
}

# Initialise vector for experiment results to be stored
answer <- numeric(5)

# Carry out experiment for a = 1, ..., 5
for(a in 1:5) {
  answer[a] <- experiment(a)
}

answer

# Plot E[Y] versus a
plot(1:5, answer, type="b", main = "Lab 4, Q2", xlab = "a", ylab="E[Y]")