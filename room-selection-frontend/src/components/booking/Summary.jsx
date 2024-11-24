function Summary({ formData, prevStep }) {
    const { name, roomType, capacity, hasComputers, hasProjectors, hasWhiteBoard, date } = formData;

    const features = [
        hasComputers && 'Computers',
        hasProjectors && 'Projectors',
        hasWhiteBoard && 'Whiteboard',
    ].filter(Boolean);

    return (
        <div>
            <h2>Summary</h2>
            <p><strong>Booking Name:</strong> {name}</p>
            <p><strong>Room Type:</strong> {roomType}</p>
            <p><strong>Capacity:</strong> {capacity}</p>
            <p><strong>Features:</strong> {features.join(', ') || 'None'}</p>
            <p><strong>Date:</strong> {new Date(date).toLocaleDateString()}</p>
            <button onClick={prevStep}>Back</button>
            <button onClick={() => alert('Booking Confirmed!')}>Confirm Booking</button>
        </div>
    );
}

export default Summary;
