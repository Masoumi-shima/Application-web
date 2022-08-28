import http from '../../http-common'

class MemberService {
    getMembers() {
        return http.get('/membres')
    }

    getAMember(permitNumber) {
        return http.get(`/membres/:${permitNumber}`)
    }

    createMember(member) {
        return http.post('/membre', member)
    }

    deleteMember(permitNumber) {
        return http.delete(`/membres/:${permitNumber}`)
    }
}

export default new MemberService()