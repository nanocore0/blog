import Cookies from 'js-cookie'

export function getToken(tokenName) {
  return Cookies.get(tokenName)
}

export function setToken(tokenName, token) {
  return Cookies.set(tokenName, token)
}

export function removeToken(tokenName) {
  return Cookies.remove(tokenName)
}
