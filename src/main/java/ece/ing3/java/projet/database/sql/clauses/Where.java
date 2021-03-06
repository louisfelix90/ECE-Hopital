package ece.ing3.java.projet.database.sql.clauses;

import ece.ing3.java.projet.database.sql.queries.SQLRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Where clause helper.
 * <p>
 * Provides a convenient way to build Where clause, reactive-style.
 * Supports where clause nesting.
 * </p>
 * <br>
 * <p>
 * Where clauses can be chained using either {@link Where#and(Where)} and {@link Where#or(Where)}, depending on the desired operator.
 * </p>
 * <p>
 * Each {@link Where#and(Where)} and {@link Where#or(Where)} have shortcut methods, respectively {@link Where#and(String, String, Object)} and {@link Where#or(String, String, Object)}, to avoid creating new unnecessary Where clauses.
 * </p>
 * <p>
 * Nesting is supported by simply chaining another Where clause.<br>
 * {@code ( new Where( "col1", "=", "val1" ) ).or( ( new Where( "col2", "{@literal <}", "val2" ) ).and( "col3", "{@literal >=}", "val3" ) ).toString();}<br>
 * will generate : {@code (col1 = 'val1' OR ((col2 {@literal <} 'val2') AND (col3 {@literal >=} 'val3')))}
 * </p>
 * <p>
 * Where supports Reactive-style programming to create more compact code.<br>
 * As such,
 * </p>
 * <pre>
 * Where o = new Where( "col1", "=", "val1" );
 * o.and( "col2", "{@literal <}", "val2" );
 * String v = o.toString();
 * </pre>
 * <p>
 * can be written
 * </p>
 * <pre>
 * String v = ( new Where( "col1", "=", "val1" ) ).and( "col2", "{@literal <}", "val2" ).toString();
 * </pre>
 * <p>
 * Nesting is still supported when using reactive-style programming.
 * </p>
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Where {
	private String query;
	private List<Object> parameters = new ArrayList<>();

	/**
	 * Creates a new chainable, empty Where clause.
	 */
	public Where() {
		query = "";
	}

	/**
	 * Creates a new chainable Where clause.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 */
	public Where( String column, String comparator, Object value ) {
		query = "(" + column + " " + comparator + " ?)";
		parameters.add( value );
	}

	/**
	 * Creates a new chainable Where clause with a subquery.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param subquery   Sub query
	 */
	public Where( String column, String comparator, SQLRequest subquery ) {
		String subqueryStr = subquery.toString();
		query = "(" + column + " " + comparator + " (" + subqueryStr.substring( 0, subqueryStr.length() - 1 ) + "))";
		List<Object> params = subquery.getParameters();
		if( params != null ) {
			parameters.addAll( params );
		}
	}

	/**
	 * Chain a new where clause using the boolean AND.
	 *
	 * @param condition Where clause to chain
	 * @return This Where clause helper
	 */
	public Where and( Where condition ) {
		if( query.length() > 0 ) {
			query += " AND ";
		}
		query += condition;
		parameters.addAll( condition.parameters );
		return this;
	}

	/**
	 * Chain a new where clause using the boolean OR.
	 *
	 * @param condition Where clause to chain
	 * @return This Where clause helper
	 */
	public Where or( Where condition ) {
		if( query.length() > 0 ) {
			query += " OR ";
		}
		query += condition;
		parameters.addAll( condition.parameters );
		return this;
	}

	/**
	 * Chain a new where clause using the boolean AND, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This Where clause helper
	 */
	public Where and( String column, String comparator, Object value ) {
		return this.and( new Where( column, comparator, value ) );
	}

	/**
	 * Chain a new where clause using the boolean OR, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This Where clause helper
	 */
	public Where or( String column, String comparator, Object value ) {
		return this.or( new Where( column, comparator, value ) );
	}

	/**
	 * Chain a new where clause using the boolean AND, using the provided values and subquery.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param subquery   Sub query
	 * @return This Where clause helper
	 */
	public Where and( String column, String comparator, SQLRequest subquery ) {
		return this.and( new Where( column, comparator, subquery ) );
	}

	/**
	 * Chain a new where clause using the boolean OR, using the provided values and subquery.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param subquery   Sub query
	 * @return This Where clause helper
	 */
	public Where or( String column, String comparator, SQLRequest subquery ) {
		return this.or( new Where( column, comparator, subquery ) );
	}

	/**
	 * Gets the corresponding SQL Where clause, for use in a prepared statement.
	 *
	 * @return SQL Order By clause
	 */
	@Override
	public String toString() {
		return query.length() > 0 ? "(" + query + ")" : "";
	}

	/**
	 * Gets the parameters to use in the prepared statement.
	 *
	 * @return Statement parameters
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * Gets the parameters to use in the prepared statement.
	 *
	 * @return Statement parameters
	 */
	public Object[] getParametersArray() {
		return parameters.toArray();
	}
}
