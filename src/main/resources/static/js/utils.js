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

let makeRandomDate = async () => {
    let counter = 0;
    let randomArtists = [];
    while (counter < 10) {
        randomArtists.push(generateRandomString(10));
        counter += 0;
    }
    for (let artist in randomArtists) {
        fetch('/artists/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(randomArtists[artist])
        })
    }

    counter = 0;
    let randomGenre = [];
    let randomGenreDesc = [];

}

let generateRandomString =(length)=>{
    let r = Math.random().toString(36).substring(length + 1);
    return r;
}