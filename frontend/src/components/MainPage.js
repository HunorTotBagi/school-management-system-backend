import React, { useContext } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import {
  List,
  ListItem,
  ListItemButton,
  ListItemText,
  Typography,
  AppBar,
  Toolbar,
  Container,
  Box,
  Button,
  Avatar,
} from "@mui/material";
import {
  Home as HomeIcon,
  Book as BookIcon,
  People as PeopleIcon,
  Logout as LogoutIcon,
} from "@mui/icons-material";
import Storage from "../Store/Storage";

const MainPage = () => {
  const storage = useContext(Storage);
  const navigate = useNavigate();

  return (
    <div className="root-page">
      <AppBar position="static" sx={{ backgroundColor: "#1E1E2F" }}>
        <Toolbar
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Button
            variant="outlined"
            onClick={() => {
              storage.setIsComicSans((previousValue) => !previousValue);
            }}
            sx={{
              color: "white",
              borderColor: "white",
              "&:hover": {
                borderColor: "#FFB74D",
                color: "#FFB74D",
              },
            }}
          >
            {storage.isComicSans
              ? "Deactivate Comic Sans"
              : "Activate Comic Sans"}
          </Button>

          <Box sx={{ display: "flex", alignItems: "center" }}>
            <List sx={{ display: "flex", padding: 0 }}>
              <ListItem>
                <ListItemButton
                  component={NavLink}
                  to="/"
                  sx={{ "&.active": { backgroundColor: "#FFB74D" } }}
                >
                  <HomeIcon sx={{ marginRight: 1 }} />
                  <ListItemText
                    primary={<Typography variant="subtitle1">Home</Typography>}
                  />
                </ListItemButton>
              </ListItem>

              {storage.isLoggedIn && (
                <ListItem disablePadding>
                  <ListItemButton
                    component={NavLink}
                    to="/subjects"
                    sx={{ "&.active": { backgroundColor: "#FFB74D" } }}
                  >
                    <BookIcon sx={{ marginRight: 1 }} />
                    <ListItemText
                      primary={
                        <Typography variant="subtitle1">Subjects</Typography>
                      }
                    />
                  </ListItemButton>
                </ListItem>
              )}

              {storage.role === "ROLE_ADMIN" && storage.isLoggedIn && (
                <ListItem disablePadding>
                  <ListItemButton
                    component={NavLink}
                    to="/parents"
                    sx={{ "&.active": { backgroundColor: "#FFB74D" } }}
                  >
                    <PeopleIcon sx={{ marginRight: 1 }} />
                    <ListItemText
                      primary={
                        <Typography variant="subtitle1">Parents</Typography>
                      }
                    />
                  </ListItemButton>
                </ListItem>
              )}
            </List>
          </Box>

          {storage.isLoggedIn && (
            <Box sx={{ display: "flex", alignItems: "center" }}>
              <Avatar sx={{ marginRight: 1 }} />
              <Button
                variant="contained"
                onClick={() => {
                  storage.setIsLoggedIn(false);
                  navigate("/");
                }}
                sx={{
                  backgroundColor: "#FFB74D",
                  "&:hover": {
                    backgroundColor: "#FFC107",
                  },
                }}
              >
                <LogoutIcon sx={{ marginRight: 1 }} />
                Logout
              </Button>
            </Box>
          )}
        </Toolbar>
      </AppBar>

      <Container className="page-content" sx={{ marginTop: 2 }}>
        <div className="outlet">
          <Outlet />
        </div>
      </Container>
    </div>
  );
};

export default MainPage;
