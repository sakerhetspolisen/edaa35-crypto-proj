library(glue)

plotresult <- function(file, start = 1) {   
  data <- read.csv(file, header=TRUE, sep=" ")
  data <- data[start:nrow(data),]
  plot(data, type = 'l')
}

getMeanAndConfidence <- function(algo, keySize, n1 = 1000, n2 = 100) {
  cmd <- glue::glue("/home/fedde/.jdks/openjdk-21.0.2/bin/java -classpath /home/fedde/Courses/edaa35/labbar/lab5/out/production/lab5 sorters.Labb {algo} {keySize} out.txt {n1}")
  means <- c()
  confidenceIntervals <- c()
  for (i in c(1:n2)) {
    system(cmd)
    parserData <- read.csv("out.txt", header=TRUE, sep=" ")
    extractedVal <- parserData[0:nrow(parserData),]
    meanVal <- mean(extractedVal$Time)
    means[i] <- meanVal
  }
  print("Mean value: ")
  finalMean <- mean(means)
  confidence <- confidenceInterval(means)
  print(confidence)
  print(finalMean)
  plotresult("out.txt", 2)
}