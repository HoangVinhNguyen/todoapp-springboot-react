import axios from 'axios';

export const axiosInstance = axios.create({
  baseURL: 'http://localhost:8088/todoapp/api',
  timeout: 5000,
  // headers: { 'X-Custom-Header': 'foobar' }
});
