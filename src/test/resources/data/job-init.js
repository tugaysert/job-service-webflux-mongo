    // create db
    db = db.getSiblingDB('job');


    // create user
    db.createUser({
        user: "job_user",
        pwd: "job_password",
        roles: [
            {
                role: "readWrite",
                db: "job"
            }
        ]
    })

    // create collection
    db.createCollection('job');

    // create docs
    db.job.insertMany(
        [
            {
                description: "senior java developer",
                company: "amazon",
                skills: ["java", "spring", "docker"],
                salary: 10000,
                isRemote: false
            },
            {
                description: "junior java developer",
                company: "apple",
                skills: ["java"],
                salary: 7000,
                isRemote: false
            },
            {
                description: "scrum master developer",
                company: "google",
                skills: ["agile", "jira"],
                salary: 8000,
                isRemote: true
            },
            {
                description: "director of eng",
                company: "microsoft",
                skills: ["java", "jira", "project"],
                salary: 20000,
                isRemote: true
            }
        ]
    )
