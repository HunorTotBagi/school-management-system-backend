import React, { useContext } from "react";
import {
  Button,
  Typography,
  Container,
  List,
  ListItem,
  ListItemText,
  Box,
} from "@mui/material";
import Storage from "../../Store/Storage";
import { useNavigate } from "react-router-dom";
import { deleteSubject } from "../../HelperFunctions/SubjectEndpoints";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

export const Subject = (props) => {
  const storage = useContext(Storage);
  const navigate = useNavigate();

  const handleDelete = async () => {
    await deleteSubject(
      localStorage.getItem("username"),
      localStorage.getItem("password"),
      props.subject.id
    );
    props.fetcherLoad.load();
  };

  return (
    <Container
      className="subject-page"
      sx={{
        mb: 2,
        p: 2,
        borderRadius: "8px",
      }}
    >
      <Typography variant="h4" component="h2" gutterBottom>
        {props.subject.name}
      </Typography>

      <Box className="subject-details" mb={2}>
        <Typography
          variant="h6"
          component="h3"
          sx={{ display: "flex", alignItems: "center" }}
        >
          Weekly Fund:
          <Typography variant="body2" sx={{ marginLeft: 1 }}>
            {props.subject.weeklyFund}
          </Typography>
        </Typography>

        <Typography variant="h6" component="h3" gutterBottom>
          Enrolled Students:
        </Typography>
        {props.subject.enrolledStudents.length === 0 ? (
          <Typography variant="body2">No enrolled students</Typography>
        ) : (
          <List>
            {props.subject.enrolledStudents.map((student, index) => (
              <ListItem key={index} sx={{ padding: "1px 0" }}>
                <ListItemText
                  primary={`${index + 1}. ${student.firstName} ${student.lastName}`}
                />
              </ListItem>
            ))}
          </List>
        )}

        <Typography variant="h6" component="h3" gutterBottom>
          Teachers:
        </Typography>
        {props.subject.teachers.length === 0 ? (
          <Typography variant="body2">No teachers</Typography>
        ) : (
          <List>
            {props.subject.teachers.map((teacher, index) => (
              <ListItem key={index} sx={{ padding: "1px 0" }}>
                <ListItemText
                  primary={`${index + 1}. ${teacher.firstName} ${teacher.lastName}`}
                />
              </ListItem>
            ))}
          </List>
        )}
      </Box>

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
              navigate("/editsubject", { state: { subject: props.subject } })
            }
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
            }}
          >
            Edit Subject
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
            Delete Subject
          </Button>
        </Box>
      )}
    </Container>
  );
};
