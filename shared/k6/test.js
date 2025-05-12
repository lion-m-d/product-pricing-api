import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  scenarios: {
    test: {
      executor: 'constant-vus',
      vus: 1,
      duration: '5s',
      exec: 'test',
      gracefulStop: '0s',
    },
    normal: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: 'normal',
      gracefulStop: '5s',
    },
    notFound: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: 'notFound',
      gracefulStop: '0s',
      startTime: '15s',
    },
    error: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: 'error',
      gracefulStop: '0s',
      startTime: '25s',
    }
  }
};

const host = "http://host.docker.internal:8080";

export function test() {
  // Test 1
  http.get(host + "/prices?applicationDate=2020-06-14T10:00:00.000&productId=35455&brandId=1");
  sleep(0.5);

  // Test 2
  http.get(host + "/prices?applicationDate=2020-06-14T16:00:00.000&productId=35455&brandId=1");
  sleep(0.5);

  // Test 3
  http.get(host + "/prices?applicationDate=2020-06-14T21:00:00.000&productId=35455&brandId=1");
  sleep(0.5);

  // Test 4
  http.get(host + "/prices?applicationDate=2020-06-15T10:00:00.000&productId=35455&brandId=1");
  sleep(0.5);

  // Test 5
  http.get(host + "/prices?applicationDate=2020-06-16T21:00:00.000&productId=35455&brandId=1");
  sleep(0.5);
}


export function normal() {
  http.get(host + "/prices?applicationDate=2020-06-14T10:00:00.000&productId=35455&brandId=1");
  sleep(0.5);
}


export function notFound() {
  http.get(host + "/prices?applicationDate=2020-06-14T10:00:00.000&productId=35455&brandId=2");
  sleep(0.5);
}


export function error() {
  http.get(host + "/prices?applicationDate=2020-00:00:00.000&productId=35455&brandId=1");
  sleep(0.5);
}
