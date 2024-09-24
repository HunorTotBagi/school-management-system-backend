import {
  Button,
  TextField,
  Typography,
  Container,
  Box,
  Stack,
} from "@mui/material";
import React, { useRef, useState } from "react";
import { putParent } from "../../HelperFunctions/ParentEndpoints";
import { useLocation } from "react-router-dom";

const EditParent = () => {
  const firstName = useRef();
  const lastName = useRef();
  const email = useRef();
  const loc = useLocation();

  const [success, setSuccess] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const [isEmptyFirstName, setIsEmptyFirstName] = useState(false);
  const [isEmptyLastName, setIsEmptyLastName] = useState(false);
  const [isEmptyEmail, setIsEmptyEmail] = useState(false);

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

    return valid;
  };

  const handleEditParent = async () => {
    if (validateInput()) {
      try {
        await putParent(
          localStorage.getItem("username"),
          localStorage.getItem("password"),
          {
            firstName: firstName.current.value,
            lastName: lastName.current.value,
            email: email.current.value,
          },
          loc.state.parent.id
        );
        setSuccess(true);
        setErrorMessage("");
      } catch (error) {
        console.log("Error:", error);
        setErrorMessage("Error updating the parent. Please try again.");
      }
    }
  };

  return (
    <Container className="edit-parent-page" maxWidth="sm" sx={{ mt: 2, mb: 2 }}>
      <Typography variant="h5" component="h1" gutterBottom>
        Edit Parent
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
            defaultValue={loc.state.parent.firstName}
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
            defaultValue={loc.state.parent.lastName}
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
            defaultValue={loc.state.parent.email}
            error={isEmptyEmail}
            helperText={isEmptyEmail && "Email must not be blank!"}
            onFocus={() => setIsEmptyEmail(false)}
            sx={{ borderRadius: 1 }}
          />

          <Button
            variant="contained"
            color="primary"
            onClick={handleEditParent}
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
              mt: 1,
            }}
          >
            Edit Parent
          </Button>
        </Stack>

        {success && (
          <Typography variant="body2" color="success.main" mt={2}>
            ✨🎉 Parent has been updated!🎉✨
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

export default EditParent;
