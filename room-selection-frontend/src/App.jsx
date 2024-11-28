import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import Login from './components/Login/Login';
import Booking from './components/Booking/Booking';
import AdminPage from './components/Pages/AdminPage';
import Home from './components/Pages/Home';
import Register from './components/Register/Register';
import './App.css';
import About from './components/Pages/About';
import Profile from './components/Profile/Profile';
import Room from './components/Room/Room';
import Navbar from "./components/Navbar/Navbar";
import Dashboard from "./components/Dashboard/Dashboard";

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));

    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
    };

    useEffect(() => {
        setIsAuthenticated(!!localStorage.getItem('token'));
    }, []);

    return (
        <Router>
            <Layout>
                <Navbar isAuthenticated={isAuthenticated} onLogout={() => setIsAuthenticated(false)} />
                <Routes>
                    <Route path="" element={<Home />} />
                    <Route path="/about" element={<About />} />
                    <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />
                    <Route path="/signup" element={<Register />} />
                    <Route
                        path="/dashboard"
                        element={isAuthenticated ? <Dashboard /> : <Navigate to="/login" />}
                    />
                    <Route path="/booking" element={<Booking />} />
                    <Route path="/profile" element={<Profile />} />
                    <Route path="/room" element={<Room />} />
                    <Route path="*" element={<Navigate to="/login" />} />
                </Routes>
            </Layout>
        </Router>
    );
};

export default App;
