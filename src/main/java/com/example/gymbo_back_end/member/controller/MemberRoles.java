package com.example.gymbo_back_end.member.controller;

public enum MemberRoles {
        USER("USER"),
        ADMIN("ADMIN");
        // Add more roles as needed

        private final String role;

        MemberRoles(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;

        }
}
