import React, { useContext, useState } from "react";
import { Button, Typography, Container, Box, Alert } from "@mui/material";
import Storage from "../../Store/Storage";
import { useNavigate } from "react-router-dom";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import { deleteParent } from "../../HelperFunctions/ParentEndpoints";

const Parent = (props) => {
  const storage = useContext(Storage);
  const navigate = useNavigate();
  const [error, setError] = useState("");

  const handleDelete = async () => {
    try {
      await deleteParent(
        localStorage.getItem("username"),
        localStorage.getItem("password"),
        props.parent.id
      );
      props.fetcherLoad.load();
    } catch (err) {
      setError(
        "Unable to delete this parent as they are currently associated with a student."
      );
    }
  };

  return (
    <Container
      className="parent-container"
      sx={{
        mb: 2,
        p: 2,
        borderRadius: "8px",
      }}
    >
      <Box className="parent-details" sx={{ marginBottom: 2 }}>
        <Typography variant="h6" component="h3">
          <strong>
            {props.parent.firstName} {props.parent.lastName}
          </strong>
        </Typography>
        <Typography variant="body2" component="p">
          <strong>Email:</strong> <span>{props.parent.email}</span>
        </Typography>
      </Box>

      {error && (
        <Alert severity="error" onClose={() => setError("")}>
          {error}
        </Alert>
      )}

      {storage.role === "ROLE_ADMIN" && (
        <Box
          className="admin-actions"
          sx={{ marginTop: 2, display: "flex", gap: 1 }}
        >
          <Button
            variant="contained"
            color="primary"
            startIcon={<EditIcon />}
            onClick={() =>
              navigate("/editparent", { state: { parent: props.parent } })
            }
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
            }}
          >
            Edit Parent
          </Button>

          <Button
            variant="contained"
            color="secondary"
            startIcon={<DeleteIcon />}
            onClick={handleDelete}
            sx={{
              backgroundColor: "#F44336",
              "&:hover": { backgroundColor: "#E53935" },
            }}
          >
            Delete Parent
          </Button>
        </Box>
      )}
    </Container>
  );
};

export default Parent;
