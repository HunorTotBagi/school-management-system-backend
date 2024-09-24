import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/v1/subjects";

export const getSubjects = async (user, password) => {
  const headers = createAuthHeader(user, password);
  return handleRequest(() => axios.get(API_BASE_URL, { headers }));
};

export const postSubject = async (user, password, data) => {
  const headers = createAuthHeader(user, password);
  return handleRequest(() => axios.post(API_BASE_URL, data, { headers }));
};

export const putSubject = async (user, password, data, id) => {
  const headers = createAuthHeader(user, password);
  return handleRequest(() =>
    axios.put(`${API_BASE_URL}/${id}`, data, { headers })
  );
};

export const deleteSubject = async (user, password, subjectId) => {
  const headers = createAuthHeader(user, password);
  return handleRequest(() =>
    axios.delete(`${API_BASE_URL}/${subjectId}`, { headers })
  );
};

const createAuthHeader = (user, password) => {
  const auth = btoa(`${user}:${password}`);
  return { Authorization: `Basic ${auth}` };
};

const handleRequest = async (requestFunction) => {
  try {
    const response = await requestFunction();
    return response;
  } catch (error) {
    console.error("API request failed:", error);
    throw error;
  }
};
