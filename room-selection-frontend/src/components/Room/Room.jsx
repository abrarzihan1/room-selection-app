import React from 'react';
import Sidebar from "../Sidebar/Sidebar";
import "./Room.css"

function Room() {
    return (
        <div className={"room-container"}>
            <Sidebar/>
            <div className={"/room-content"}>
                <h1>No content added yet</h1>
            </div>
        </div>
    );
}

export default Room;