import java.sql.*;

/*
res.close();
statement.close();
connection.close();
I den rekkefølgen!

try catch på alle sammen, finally etter hver
*/

/**
Største problemet med transaksjonshåndtering er at man ikke gjør det.
Kan ta mange år før det oppstår problemer ved å ikke gjøre det.

1. Skru av automcommit
Connection forbindelse = DriverManager.getConnection(databasenavn);
forbindelse.setAutoCommit(false);

....

forbindelse.commit();

// Hvis det ikke gikk bra
catch(SQLException e){
	Opprydder.rullTiblake(forbindelse);
} finally { // Rydd opp
	Opprydder.settAutoCommit(forbindelse)
	Opprydder.close();
	etc...
}




*/

