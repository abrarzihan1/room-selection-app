import React from 'react';
import './Layout.css'

function Layout({ children }) {
    return (
        <div className="layout">
            <main className={"main"}>{children}</main>
        </div>
    )
}

export default Layout;
