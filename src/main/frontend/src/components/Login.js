import React from 'react';
import './Login.css';

const Login = () => {
    return (
        <div className="login-container">
            <div className="login-box">
                <h2>TeachSpace</h2>
                <form>
                    <div className="input-container">
                        <label>Email</label>
                        <input type="email" placeholder="Enter your email" />
                    </div>
                    <div className="input-container">
                        <label>Password</label>
                        <input type="password" placeholder="Enter your password" />
                    </div>
                    <button type="submit" className="login-button">Login</button>
                </form>
                <div className="signup-link">
                    Don't have an account? <a href="/signup">Register here</a>
                </div>
            </div>
        </div>
    );
};

export default Login;
