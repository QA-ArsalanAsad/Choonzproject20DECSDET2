export let clearAuth =()=>{
    sessionStorage.setItem('auth', null);
    location.reload();
}