#!/bin/bash

factorial() {
  result=1
  for ((i=2; i<=$1; i++)); do
    result=$(($result * i))
  done
  echo $result
}

echo "Choose an operation:"
echo "+ for addition"
echo "- for subtraction"
echo "* for multiplication"
echo "/ for division"
echo "% for modulo (remainder)"
echo "! for factorial"
read operand

if [ "$operand" = "!" ]; then
  echo "Enter a number for factorial:"
  read num1
else
  echo "Enter the first number:"
  read num1
  echo "Enter the second number:"
  read num2
fi

if [ "$operand" = "+" ]; then
  result=$(($num1 + $num2))  
elif [ "$operand" = "-" ]; then
  result=$(($num1 - $num2))
elif [ "$operand" = "*" ]; then
  result=$(($num1 * $num2))  
elif [ "$operand" = "/" ]; then
  result=$(($num1 / $num2))  
elif [ "$operand" = "%" ]; then
  result=$(($num1 % $num2))  
elif [ "$operand" = "!" ]; then
  result=$(factorial $num1) 
else
  echo "Invalid operation"
  exit 1
fi

echo "Result: $result"
