import {
  Button,
  TextField,
  Typography,
  Container,
  Box,
  Stack,
} from "@mui/material";
import React, { useRef, useState } from "react";
import { postParent } from "../../HelperFunctions/ParentEndpoints";
import { useNavigate } from "react-router-dom";

const NewParent = () => {
  const firstName = useRef();
  const lastName = useRef();
  const email = useRef();
  const password = useRef();

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const [isEmptyFirstName, setIsEmptyFirstName] = useState(false);
  const [isEmptyLastName, setIsEmptyLastName] = useState(false);
  const [isEmptyEmail, setIsEmptyEmail] = useState(false);
  const [isEmptyPassword, setIsEmptyPassword] = useState(false);

  const navigation = useNavigate();

  const validateInput = () => {
    let valid = true;

    if (firstName.current.value.length === 0) {
      setIsEmptyFirstName(true);
      valid = false;
    }

    if (lastName.current.value.length === 0) {
      setIsEmptyLastName(true);
      valid = false;
    }

    if (email.current.value.length === 0) {
      setIsEmptyEmail(true);
      valid = false;
    }

    if (password.current.value.length === 0) {
      setIsEmptyPassword(true);
      valid = false;
    }

    return valid;
  };

  const handleAddParent = async () => {
    if (validateInput()) {
      try {
        await postParent(
          localStorage.getItem("username"),
          localStorage.getItem("password"),
          {
            firstName: firstName.current.value,
            lastName: lastName.current.value,
            email: email.current.value,
            password: password.current.value,
          }
        );
        setSuccessMessage(
          `✨🎉 Successfully added ${firstName.current.value} ${lastName.current.value} to the list of parents! 🎉✨`
        );

        firstName.current.value = "";
        lastName.current.value = "";
        email.current.value = "";
        password.current.value = "";
        setErrorMessage("");

        setTimeout(() => {
          navigation(-1);
        }, 2000);
      } catch (error) {
        console.log("Error:", error);
        setErrorMessage("Error adding the parent. Please try again.");
      }
    }
  };

  return (
    <Container className="new-parent-page" maxWidth="sm" sx={{ mt: 2, mb: 2 }}>
      <Typography variant="h5" component="h1" gutterBottom>
        Add New Parent
      </Typography>

      <Box className="form-container" component="form" mt={2}>
        <Stack spacing={1}>
          <TextField
            label="First Name"
            required
            fullWidth
            color="primary"
            variant="outlined"
            inputRef={firstName}
            error={isEmptyFirstName}
            helperText={isEmptyFirstName && "First name must not be blank!"}
            onFocus={() => setIsEmptyFirstName(false)}
            sx={{ borderRadius: 1 }}
          />

          <TextField
            label="Last Name"
            required
            fullWidth
            color="primary"
            variant="outlined"
            inputRef={lastName}
            error={isEmptyLastName}
            helperText={isEmptyLastName && "Last name must not be blank!"}
            onFocus={() => setIsEmptyLastName(false)}
            sx={{ borderRadius: 1 }}
          />

          <TextField
            label="Email"
            required
            fullWidth
            color="primary"
            variant="outlined"
            inputRef={email}
            error={isEmptyEmail}
            helperText={isEmptyEmail && "Email must not be blank!"}
            onFocus={() => setIsEmptyEmail(false)}
            sx={{ borderRadius: 1 }}
          />

          <TextField
            label="Password"
            required
            fullWidth
            color="primary"
            variant="outlined"
            inputRef={password}
            type="password"
            error={isEmptyPassword}
            helperText={isEmptyPassword && "Password must not be blank!"}
            onFocus={() => setIsEmptyPassword(false)}
            sx={{ borderRadius: 1 }}
          />

          <Button
            variant="contained"
            color="primary"
            onClick={handleAddParent}
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
              mt: 1,
            }}
          >
            Add Parent
          </Button>
        </Stack>

        {successMessage && (
          <Typography variant="body2" color="success.main" mt={2}>
            {successMessage}
          </Typography>
        )}

        {errorMessage && (
          <Typography variant="body2" color="error.main" mt={1}>
            {errorMessage}
          </Typography>
        )}
      </Box>
    </Container>
  );
};

export default NewParent;
