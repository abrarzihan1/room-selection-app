import React from 'react';
import './Login.css';  // Import the CSS file for styling

const Login = () => {
    return (
        <div className="login-container">
            <h2>Teacher Login</h2>
            <form action="/login" method="POST">
                <label htmlFor="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required />

                <label htmlFor="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required />

                <input type="submit" value="Login" />
            </form>
            <p>Don't have an account? <a href="/signup">Register here</a></p>
        </div>
    );
};

export default Login;
