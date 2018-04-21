package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class TableModel<M extends Model> extends AbstractTableModel {
	private Class<? extends Model> modelClass;
	private List<M> instances;
	private List<Map<String, Object>> rows;

	private String[] fieldNames;

	public TableModel( Class<? extends Model> modelClass ) {
		this.modelClass = modelClass;
		fieldNames = Model.getFieldNames( getModelClass() );
	}

	public List<M> getList() {
		return instances;
	}

	public void setList( Map.Entry<List<M>, List<Map<String, Object>>> instances ) {
		this.instances = instances.getKey();
		this.rows = instances.getValue();
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	String[] getFieldNames() {
		return fieldNames;
	}

	List<Map<String, Object>> getRows() {
		return rows;
	}

	@Override
	public String getColumnName( int col ) {
		return Strings.get( Constants.MODEL_STRINGS_PREFIX + getFieldNames()[ col ] );
	}

	@Override
	public Class<?> getColumnClass( int columnIndex ) {
		PropertyDescriptor desc = Model.getPropertyDescriptor( getModelClass(), getFieldNames()[ columnIndex ] );
		return ( desc != null ? desc.getPropertyType() : Object.class );
	}

	@Override
	public int getRowCount() {
		return instances != null ? instances.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return getFieldNames().length;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		M s = instances.get( row );

		try {
			return PropertyUtils.getSimpleProperty( s, getFieldNames()[ col ] );
		} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			e.printStackTrace();
		}

		return null;
	}

	public Class<? extends Model> getModelClass() {
		return modelClass;
	}
}