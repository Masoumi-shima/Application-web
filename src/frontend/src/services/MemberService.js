import http from '../../http-common'

class MemberService {
    getMembers() {
        return http.get('/membres')
    }

    getAMember(permitNumber) {
        return http.get(`/membres/:${permitNumber}`)
    }
}

export default new MemberService()