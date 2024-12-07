import React, { useEffect } from 'react';
import Sidebar from "../Sidebar/Sidebar";
import "./Room.css"
import axios from "axios";

function Room() {
    const [rooms, setRooms] = React.useState([]);
    const [loading, setLoading] = React.useState(true);
    const [searchQuery, setSearchQuery] = React.useState("");

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const response = await axios.get(
                    "api/private/room/getAll",{
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`,
                        },
                        withCredentials: true
                    }
                );
                setRooms(response.data);
                setLoading(false);  // Data has been fetched, set loading to false
            } catch (error) {
                console.error("Error fetching rooms. Please try again later.");
                setLoading(false);  // In case of error, stop loading
            }
        };

        fetchRooms();
    }, []);

    const filteredRooms = rooms.filter((room) =>
        room.roomId.toLowerCase().includes(searchQuery.toLowerCase())
    );

    return (
        <div className={"dashboard-container"}>
            <Sidebar />
            <div className={"dashboard-content"}>
                <h1>Rooms</h1>
                <p className={"dashboard-text"}>You can details of all of the rooms in this page. You can also search for a particular room with its room ID.</p>

                <div className="room-search-container">
                    <input
                        type="text"
                        placeholder="Search by Room ID"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        className="room-search-input"
                    />
                </div>

                {loading ? (
                    <div className="loading-message">Loading...</div>
                ) : (
                    <>
                        <div className={"room-table-title-row"}>
                            <div className="room-table-title-name">Room ID</div>
                            <div className="room-table-title-name">Capacity</div>
                            <div className="room-table-title-name">Room Type</div>
                            <div className="room-table-title-name">Computers</div>
                            <div className="room-table-title-name">Projectors</div>
                            <div className="room-table-title-name">WhiteBoard</div>
                        </div>

                        {filteredRooms.length > 0 ? (
                            filteredRooms.map((room, index) => {
                                return (
                                    <div className='room-table-item-row' key={index}>
                                        <div className='room-table-item-name'>{room.roomId}</div>
                                        <div className='room-table-item-name'>{room.capacity}</div>
                                        <div className='room-table-item-name'>{room.roomType}</div>
                                        <div className='room-table-item-name'>{room.hasComputers ? 'Yes' : 'No'}</div>
                                        <div className='room-table-item-name'>{room.hasProjectors ? 'Yes' : 'No'}</div>
                                        <div className='room-table-item-name'>{room.hasWhiteBoard ? 'Yes' : 'No'}</div>
                                    </div>
                                );
                            })
                        ) : (
                            <div className="room-search-no-results">No results found</div>
                        )}
                    </>
                )}
            </div>
        </div>
    );
}

export default Room;
