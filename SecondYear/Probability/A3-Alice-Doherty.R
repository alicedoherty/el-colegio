#
# Question 1
# Percentage versus k gives an exponential graph
#

# Calculates percentage of time we would use first lamp
# p = 100 * (t1/t_total)
first_lamp <- function(k) {
  number_of_samples <- 10000
  lambda <- 1/24000
  
  # Draws 10,000 random numbers (lamps) from exponential distribution
  data <- rexp (number_of_samples, lambda)
  
  # Pick k random lamps from numbers generated above
  k_lamps <- sample(data, k)
  
  # Set first lamp's lifetime to t1
  t1 <- k_lamps[1]
  
  # Iterate through all k lamps and calculate total lifetime
  total_t <- 0
  for (x in 1:k) {
    total_t <- k_lamps[x] + total_t
  }
  
  # Calculate percentage of time we would use first lamp
  p <- 100*(t1/total_t)
  
  return(p)
}

# Calculate mean of results for 200 iterations
experiment <- function(k) {
  iterations <- 200
  results <- replicate(iterations, first_lamp(k))
  probability <- sum(results/iterations)
  return(probability)
}

# Initialise vector for experiment results to be stored
answer <- numeric(4)

# Carry out experiment for k = 2, ..., 5
for(k in 2:5) {
  answer[k-1] <- experiment(k)
}

answer

# Plot percentage versus k
plot(2:5, answer, type="b", main = "Lab 3, Q1", xlab = "k", ylab="Percentage time to use first lamp")



#
# Question 2
# Values for which P(a<X<b) is maximum
#  sigma ~ 1.48
#  var ~ 2.19
#

sigma_experiment <- function(sigma) {
  n <- 10000
  mean <- 0
  a <- 1
  b <- 2
  
  # Draws 10,000 random numbers from normal distribution generated
  # using sigma passed into function
  data <- rnorm(n, mean, sigma)
  
  # Returns count of numbers generated above that are between a and b, (a<X<b)
  count <- sum(data > a & data < b)
  
  # Calculate and return P(a<X<b)
  p <- count/n
  return(p)
}

experiment <- function() {
  # Initialise max variables
  max_sigma <- 0
  max_probability <- 0
  
  # Iterates through different values of sigma (0.01:3)
  # Calls function to generate normal distribution with that sigma and returns
  # P(a<X<b)
  for(i in 1:300) {
    sigma <- 0.01*i
    p <- sigma_experiment(sigma)
    
    # If probability returned is greater than current max, update max variables
    if(p > max_probability) {
      max_probability <- p
      max_sigma <- sigma
    }
  }
  return(max_sigma)
}

# Calculate mean of results for 200 iterations
iterations <- 200
results <- replicate(iterations, experiment())
avrg_sigma <- sum(results)/iterations

# Calculate variance value 
max_var <- avrg_sigma * avrg_sigma
max_var