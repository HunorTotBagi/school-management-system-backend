import axios from "axios";

export const getLogin = async (user, password) => {
  const auth = btoa(`${user}:${password}`);

  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/users/email/${user}`,
      {
        headers: { Authorization: `Basic ${auth}` },
      }
    );

    return response;
  } catch (error) {
    console.error("Invalid credencials:", error);
    throw error;
  }
};
