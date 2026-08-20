# Supabase Implementation Guide for Slambook Backend

This guide explains how to connect your existing Spring Boot application (`slambook`) to a Supabase PostgreSQL database. Your application is already perfectly set up for this, as it uses PostgreSQL and reads configurations from environment variables!

## Prerequisites
1. A [Supabase](https://supabase.com/) account.

## Step 1: Create a Supabase Project
1. Log in to your [Supabase Dashboard](https://app.supabase.com/).
2. Click on **New Project**.
3. Select your organization and provide a project name (e.g., `Slambook`).
4. Generate a strong **Database Password** and save it safely.
5. Choose a region close to your users and click **Create new project**.
6. Wait a few minutes for the database to be provisioned.

## Step 2: Get Database Connection Details
Once your project is ready, you need the connection string for your application:
1. In the Supabase dashboard, go to the **Project Settings** (gear icon) on the left sidebar.
2. Click on **Database** under the Configuration section.
3. Scroll down to the **Connection string** section.
4. Select the **JDBC** tab.
5. You will see a connection string that looks like this:
   `jdbc:postgresql://db.[YOUR_PROJECT_REF].supabase.co:5432/postgres?user=postgres&password=[YOUR-PASSWORD]`

### IMPORTANT: Connection Pooling / IPv4
Supabase is transitioning to IPv6-only databases. If your local development environment or hosting provider does not support IPv6, you will need to use Supabase's connection pooler (Supavisor) which provides an IPv4 address.
To get the IPv4 connection string:
1. Go to **Project Settings -> Database**.
2. Scroll to **Connection string**.
3. Enable **Use connection pooling** and set Mode to `Transaction` or `Session`.
4. Copy the JDBC URI. It usually looks like: `jdbc:postgresql://aws-0-[REGION].pooler.supabase.com:5432/postgres`
5. Note that the username for the pooler will be in the format `postgres.[YOUR_PROJECT_REF]`.

## Step 3: Update your `.env` File
Your application reads database credentials from the `.env` file. We need to update these values to point to Supabase.

Open the `.env` file in the root of your project:

```env
# Change DB_URL to your Supabase JDBC connection string
# IMPORTANT: Remove the "?user=..." and "&password=..." parts from the end of the JDBC string, 
# because your application.properties passes these properties separately.
# Add "?sslmode=require" for a secure connection.
DB_URL=jdbc:postgresql://aws-0-[REGION].pooler.supabase.com:5432/postgres?sslmode=require

# Update with your Supabase database user 
# (e.g. 'postgres' for direct connection, or 'postgres.[YOUR_PROJECT_REF]' for pooled connection)
DB_USERNAME=postgres.[YOUR_PROJECT_REF]

# Update with the database password you created in Step 1
DB_PASSWORD=your_supabase_password

# Keep your existing CORS settings
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:5173
```

## Step 4: Test the Connection
Run your Spring Boot application locally to verify it connects to Supabase.

```bash
./mvnw spring-boot:run
```

Since you have `spring.jpa.hibernate.ddl-auto=update` in your `application.properties`, Hibernate will automatically connect to your Supabase database and create or update the necessary tables based on your entity classes.

## Step 5: Verify Tables in Supabase
1. Go back to your Supabase Dashboard.
2. Click on the **Table Editor** (grid icon) on the left sidebar.
3. You should see all the tables created by your Spring Boot application successfully migrated into Supabase!

## Optional: Using Supabase Auth or Storage
Currently, this guide sets up Supabase as a managed PostgreSQL database, which requires zero code changes to your Spring Boot app. 
If you later want to use Supabase's built-in Authentication, Storage, or Edge Functions from your Java application, you can integrate Supabase Java SDKs or make direct HTTP calls to the Supabase REST API using your Project URL and Anon/Service-Role API keys.
