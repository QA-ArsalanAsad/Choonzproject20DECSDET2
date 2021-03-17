// Function that hash's passwords
export let passwordToHash = async (password) => {
    const msgUint8 = new TextEncoder().encode(password);
    const hashBuffer = await crypto.subtle.digest('SHA-256', msgUint8);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
    return hashHex;
}

export let getUsernameFromAuth = async (authToken) => {
    let username;
    await fetch(`/user/read/auth/${authToken}`)
        .then((response) => {
            return response.json();
        }).then((responseData) => {
        username = responseData['userName'];
    })
    return username;
}

export let getAllTrackNames = async ()=>{
    let allTrackNames = [];
    await fetch('/tracks/read')
        .then((response)=>{
            return response.json();
        }).then((responseData)=>{
            for (let track in responseData) {
                allTrackNames.push(responseData[track]['name']);
            }
        })
    return allTrackNames;
}