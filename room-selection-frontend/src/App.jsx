import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import Login from './components/Login/Login';
import Dashboard from './components/Dashboard/Dashboard';
import Booking from './components/booking/Booking';
import BookingForm from './components/booking/BookingForm';
import BookingRoom from './components/booking/BookingRoom';
import AdminPage from './components/Pages/AdminPage';
import Home from './components/Pages/Home';
import Register from './components/Register/Register';
import './App.css'
import About from "./components/Pages/About";

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
                <Routes>
                    <Route path="/home" element={<Home />} />
                    <Route path={"/about"} element={<About />} />
                    <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />
                    <Route path="/signup" element={<Register />} />
                    <Route
                        path="/dashboard"
                        element={isAuthenticated ? <AdminPage /> : <Navigate to="/login" />}
                    />
                    <Route path="/booking/time" element={<Booking />} />
                    <Route path="/booking/form" element={<BookingForm />} />
                    <Route path="/booking/room" element={<BookingRoom />} />
                    <Route path="*" element={<Navigate to="/login" />} />
                </Routes>
            </Layout>
        </Router>
    );
};

export default App;
