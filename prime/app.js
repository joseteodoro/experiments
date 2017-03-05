'use strict';

function eratosthenesSieve (n) {
  // Eratosthenes algorithm to find all primes under n
  let array = [];
  let upperLimit = Math.sqrt(n);
  let output = [];

  // Make an array from 2 to (n - 1)
  for (let i = 0; i < n; i++) {
    array.push(true);
  }

  // Remove multiples of primes starting from 2, 3, 5,...
  for (let i = 2; i <= upperLimit; i++) {
    if (array[i]) {
      for (let j = i * i; j < n; j += i) {
        array[j] = false;
      }
    }
  }

  // All array[i] set to true are primes
  for (let i = 2; i < n; i++) {
    if (array[i]) {
      output.push(i);
    }
  }

  return output;
}

let max = 10000000;

let primes = eratosthenesSieve(max);
let threshold = max / 2;

let primesLess = primes.filter(function (v) {
  return v < threshold;
});

primesLess.forEach(function (element) {
  let filtered = primesLess.filter(function (v) {
    return v < element;
  });

  // output is
  // nao funciona para 2
  // nao funciona para 3
  let valid = false;
  filtered.forEach(function (prime) {
    let proposal = ((element * 2) - prime);
    let includes = primes.lastIndexOf(proposal);
    if (!valid && includes !== -1) {
      valid = true;
    }
  });
  if (!valid) {
    console.log(`nao funciona para ${element}`);
  }
});
